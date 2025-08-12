package song.sj;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import song.sj.entity.Address;
import song.sj.entity.ItemCategory;
import song.sj.entity.Shop;
import song.sj.entity.ShopItemCategory;
import song.sj.repository.ItemCategoryRepository;
import song.sj.repository.ShopItemCategoryRepository;
import song.sj.repository.ShopRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitialDataLoader implements CommandLineRunner {

    private final ItemCategoryRepository itemCategoryRepository;
    private final ShopItemCategoryRepository shopItemCategoryRepository;
    private final ShopRepository shopRepository;

    @Override
    public void run(String... args) throws Exception {

        if (itemCategoryRepository.findByItemCategoryName("ACCESSORY") == null) {
            itemCategoryRepository.save(ItemCategory.builder()
                    .itemCategoryName("ACCESSORY")
                    .build());
        }
        if (itemCategoryRepository.findByItemCategoryName("BAG") == null) {
            itemCategoryRepository.save(ItemCategory.builder()
                    .itemCategoryName("BAG")
                    .build());
        }if (itemCategoryRepository.findByItemCategoryName("BOTTOM") == null) {
            itemCategoryRepository.save(ItemCategory.builder()
                    .itemCategoryName("BOTTOM")
                    .build());
        }if (itemCategoryRepository.findByItemCategoryName("ETC") == null) {
            itemCategoryRepository.save(ItemCategory.builder()
                    .itemCategoryName("ETC")
                    .build());
        }if (itemCategoryRepository.findByItemCategoryName("OUTER") == null) {
            itemCategoryRepository.save(ItemCategory.builder()
                    .itemCategoryName("OUTER")
                    .build());
        }
        if (itemCategoryRepository.findByItemCategoryName("SHOES") == null) {
            itemCategoryRepository.save(ItemCategory.builder()
                    .itemCategoryName("SHOES")
                    .build());
        }
        if (itemCategoryRepository.findByItemCategoryName("TOP") == null) {
            itemCategoryRepository.save(ItemCategory.builder()
                    .itemCategoryName("TOP")
                    .build());
        }

        if (shopRepository.findByShopName("1번샵").isEmpty()) {
            List<ShopItemCategory> shopItemCategoryList = new ArrayList<>();

            shopRepository.save(Shop.builder()
                    .shopName("1번샵")
                    .shopDescription("여기는 1번샵")
                    .address(Address.builder()
                            .city("서울시")
                            .street("1번가")
                            .zipcode("11")
                            .build())
                    .ownerMemberId(2L)
                    .totalReviewCount(10)
                    .totalWishlistCount(78)
                    .totalGradeSum(5)
                    .averageGrade(5)
                    .build());

            Shop shop = shopRepository.findByShopName("1번샵").orElseThrow(() -> new IllegalArgumentException(""));

            shopItemCategoryRepository.save(ShopItemCategory.builder()
                    .shop(shop)
                    .itemCategory(itemCategoryRepository.findByItemCategoryName("OUTER"))
                    .build());

            shopItemCategoryRepository.save(ShopItemCategory.builder()
                    .shop(shop)
                    .itemCategory(itemCategoryRepository.findByItemCategoryName("Top"))
                    .build());
        }

        if (shopRepository.findByShopName("2번샵").isEmpty()) {
            List<ShopItemCategory> shopItemCategoryList = new ArrayList<>();

            shopRepository.save(Shop.builder()
                    .shopName("2번샵")
                    .shopDescription("여기는 2번샵")
                    .address(Address.builder()
                            .city("서울시")
                            .street("2번가")
                            .zipcode("22")
                            .build())
                    .ownerMemberId(3L)
                    .totalReviewCount(8)
                    .totalWishlistCount(52)
                    .totalGradeSum(4)
                    .averageGrade(4)
                    .build());

            Shop shop = shopRepository.findByShopName("2번샵").orElseThrow(() -> new IllegalArgumentException(""));

            shopItemCategoryRepository.save(ShopItemCategory.builder()
                    .shop(shop)
                    .itemCategory(itemCategoryRepository.findByItemCategoryName("OUTER"))
                    .build());

            shopItemCategoryRepository.save(ShopItemCategory.builder()
                    .shop(shop)
                    .itemCategory(itemCategoryRepository.findByItemCategoryName("BOTTOM"))
                    .build());
        }

        if (shopRepository.findByShopName("3번샵").isEmpty()) {
            List<ShopItemCategory> shopItemCategoryList = new ArrayList<>();

            shopRepository.save(Shop.builder()
                    .shopName("3번샵")
                    .shopDescription("여기는 3번샵")
                    .address(Address.builder()
                            .city("서울시")
                            .street("3번가")
                            .zipcode("33")
                            .build())
                    .ownerMemberId(4L)
                    .totalReviewCount(17)
                    .totalWishlistCount(121)
                    .totalGradeSum(5)
                    .averageGrade(5)
                    .build());

            Shop shop = shopRepository.findByShopName("3번샵").orElseThrow(() -> new IllegalArgumentException(""));

            shopItemCategoryRepository.save(ShopItemCategory.builder()
                    .shop(shop)
                    .itemCategory(itemCategoryRepository.findByItemCategoryName("OUTER"))
                    .build());

            shopItemCategoryRepository.save(ShopItemCategory.builder()
                    .shop(shop)
                    .itemCategory(itemCategoryRepository.findByItemCategoryName("BOTTOM"))
                    .build());

            shopItemCategoryRepository.save(ShopItemCategory.builder()
                    .shop(shop)
                    .itemCategory(itemCategoryRepository.findByItemCategoryName("Top"))
                    .build());
        }
    }
}
