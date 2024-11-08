package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopCategoryMiddleTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_category_middle_table_id")
    private Long id;

    @JoinColumn(name = "shopCategory_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ShopCategory shopCategory;

    @JoinColumn(name = "shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    public void addShopCategory(ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
        shopCategory.getShopCategoryMiddleTableList().add(this);
    }

    public void addShop(Shop shop) {
        this.shop = shop;
        shop.getShopCategoryMiddleTableList().add(this);
    }
}
