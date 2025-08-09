package song.sj.dto.shop_item_category;

import lombok.Builder;
import lombok.Data;
import song.sj.entity.Shop;

@Data
@Builder
public class ShopItemCategorySaveDto {

    private Shop shop;
    private Long shopItemCategoryId;
}
