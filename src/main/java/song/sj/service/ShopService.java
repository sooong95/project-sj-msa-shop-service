package song.sj.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import song.sj.dto.shop.ShopSaveDto;
import song.sj.entity.Shop;
import song.sj.entity.ShopCategory;
import song.sj.entity.ShopCategoryMiddleTable;
import song.sj.entity.ShopImages;
import song.sj.enums.ItemValue;
import song.sj.repository.ShopCategoryMiddleTableServiceRepository;
import song.sj.repository.ShopCategoryRepository;
import song.sj.repository.ShopImageRepository;
import song.sj.repository.ShopRepository;
import song.sj.service.image.ImageFile;
import song.sj.service.toEntity.ToShop;

import java.io.IOException;
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
    private final ShopCategoryMiddleTableServiceRepository shopCategoryMiddleTableServiceRepository;
    private final ShopCategoryRepository shopCategoryRepository;

    private void shopCategorySave(String shopCategoryName, Shop shop) {

        ShopCategory shopCategory = shopCategoryRepository.findByShopCategoryName(shopCategoryName);
        ShopCategoryMiddleTable shopCategoryMiddleTable = shopCategoryMiddleTableServiceRepository.save(new ShopCategoryMiddleTable(shopCategory, shop));

        shopCategoryMiddleTable.addShop(shop);
        shopCategoryMiddleTable.addShopCategory(shopCategory);
    }

    public void save(ShopSaveDto shopSaveDto) {

        Shop shop = ToShop.toShopEntity(shopSaveDto);

        shopRepository.save(shop);

        shop.addShop(memberService.findMember(memberService.getMemberFromJwt().getEmail()));

        shopSaveDto.getMainEvent().forEach(value -> shopCategorySave(value.toString(), shop));
    }

    public void saveShopImages(Long id, List<MultipartFile> files) {
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
