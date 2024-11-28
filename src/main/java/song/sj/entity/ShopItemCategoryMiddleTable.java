package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopItemCategoryMiddleTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_item_category_middle_table_id")
    private Long id;

    @JoinColumn(name = "itemCategory_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ItemCategory itemCategory;

    @JoinColumn(name = "shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    public ShopItemCategoryMiddleTable(ItemCategory itemCategory, Shop shop) {
        this.itemCategory = itemCategory;
        this.shop = shop;
    }

    public void addShopItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
        itemCategory.getShopItemCategoryMiddleTableList().add(this);
    }

    public void addShop(Shop shop) {
        this.shop = shop;
        shop.getShopCategoryMiddleTableList().add(this);
    }
}
