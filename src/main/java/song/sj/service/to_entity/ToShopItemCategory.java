package song.sj.service.to_entity;

import song.sj.dto.shop_item_category.ShopItemCategorySaveDto;
import song.sj.entity.ShopItemCategory;

public class ToShopItemCategory {

    public static ShopItemCategory toShopItemCategoryEntity(ShopItemCategorySaveDto shopItemCategorySaveDto) {
        return ShopItemCategory.builder()
                .shop(shopItemCategorySaveDto.getShop())
                .itemCategoryId(shopItemCategorySaveDto.getShopItemCategoryId())
                .build();
    }
}
