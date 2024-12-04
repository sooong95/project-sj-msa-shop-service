package song.sj.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import song.sj.dto.shop.ShopSaveDto;
import song.sj.entity.*;
import song.sj.repository.*;
import song.sj.service.image.ImageFile;
import song.sj.service.toEntity.ToShop;

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
    private final MemberService memberService;
    private final ShopItemCategoryMiddleTableServiceRepository shopItemCategoryMiddleTableServiceRepository;
    private final ItemCategoryRepository itemCategoryRepository;

    private void shopItemCategorySave(String itemCategoryName, Shop shop) {

        ItemCategory itemCategory = itemCategoryRepository.findByItemCategoryName(itemCategoryName);
        ShopItemCategoryMiddleTable shopItemCategoryMiddleTable = shopItemCategoryMiddleTableServiceRepository.save(new ShopItemCategoryMiddleTable(itemCategory, shop));

        shopItemCategoryMiddleTable.addShop(shop);
        shopItemCategoryMiddleTable.addShopItemCategory(itemCategory);
    }

    public void save(ShopSaveDto shopSaveDto) {

        Shop shop = ToShop.toShopEntity(shopSaveDto);

        shopRepository.save(shop);

        shop.addShop(memberService.findMember(memberService.getMemberFromJwt().getEmail()));

        shopSaveDto.getMainEvent().forEach(value -> shopItemCategorySave(value.toString(), shop));
    }

    public void saveShopImages(Long id, List<MultipartFile> files) throws AccessDeniedException {

        List<Long> idList = memberService.getMemberFromJwt().getShopList().stream().map(Shop::getId).toList();

        if (!idList.contains(id)) throw new AccessDeniedException("권한이 없습니다");

        addShopImage(files, shopRepository.findById(id).orElseThrow());
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
        shop.changeShopName(dto.getShopName());
        shop.changeShopDescription(dto.getShopDescription());
        shop.changeMainEvent(dto.getMainEvent());
        shop.changeAddress(dto.getAddress());
    }
}
