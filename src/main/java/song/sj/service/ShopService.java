package song.sj.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import song.sj.dto.shop.ShopSaveDto;
import song.sj.dto.shop_item_category.ShopItemCategorySaveDto;
import song.sj.entity.*;
import song.sj.repository.*;
import song.sj.service.feign.ItemServiceFeign;
import song.sj.service.image.ImageFile;
import song.sj.service.to_entity.ToShop;
import song.sj.service.to_entity.ToShopItemCategory;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final ImageFile imageFile;
    private final ShopImageRepository shopImageRepository;
    private final ItemServiceFeign itemServiceFeign;
    private final ShopItemCategoryRepository shopItemCategoryRepository;
    private final ItemCategoryRepository itemCategoryRepository;

    private void shopItemCategorySave(List<ItemCategory> itemCategoryNameList, Shop shop) {

        for (ItemCategory itemCategory : itemCategoryNameList) {

            ItemCategory findItemCategory = itemCategoryRepository.findByItemCategoryName(itemCategory.getItemCategoryName());

            shopItemCategoryRepository.save(ToShopItemCategory.toShopItemCategoryEntity(ShopItemCategorySaveDto.builder()
                            .itemCategory(findItemCategory)
                            .shop(shop)
                    .build()));
        }
    }

    public void save(Long userId, ShopSaveDto shopSaveDto) {

        Shop shop = ToShop.toShopEntity(shopSaveDto);

        shopRepository.save(shop);

        shop.addOwnerMemberId(userId);

        shopItemCategorySave(shopSaveDto.getItemCategoryName(), shop);
    }

    public void saveShopImages(Long shopId, List<MultipartFile> files) throws AccessDeniedException {

        if (shopRepository.findById(shopId).isEmpty()) throw new AccessDeniedException("권한이 없습니다");

        addShopImage(files, shopRepository.findById(shopId).orElseThrow());
    }

    private void addShopImage(List<MultipartFile> files, Shop shop) {
        try {
            for (MultipartFile file : files) {
                ShopImages shopImages = imageFile.serverFile(file, ShopImages.class);
                shopImageRepository.save(shopImages);
                shop.addImage(shopImageRepository.findById(shopImages.getId()).orElseThrow());
            }
        } catch (IOException e) {
            log.info("addShopImage error={}", e.getMessage());
        }
    }

    public void deleteImage(Long id) {

        ShopImages shopImage = shopImageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("이미지를 찾을 수 없습니다."));

        Shop shop = shopImage.getShop();

        shop.deleteImage(shopImage);

        shopImageRepository.delete(shopImage);
    }

    public void updateShop(Long id, ShopSaveDto dto) {
        Shop shop = shopRepository.findById(id).orElseThrow();
        ShopItemCategory shopItemCategory = ShopItemCategory.builder().build();
        shop.changeShopName(dto.getShopName());
        shop.changeShopDescription(dto.getShopDescription());
        dto.getItemCategoryName().stream().forEach(i -> shopItemCategory.addShopItemCategory(shop, i));
        shop.changeAddress(dto.getAddress());
    }
}
