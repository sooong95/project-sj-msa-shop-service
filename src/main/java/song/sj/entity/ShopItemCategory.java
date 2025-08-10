package song.sj.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_item_id")
    private Long id;

    @JoinColumn(name = "shop_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @JoinColumn(name = "item_category_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ItemCategory itemCategory;

    public void addShopItemCategory(Shop shop, ItemCategory itemCategory) {
        this.shop = shop;
        this.itemCategory = itemCategory;
        shop.getShopItemCategoryList().add(this);
    }
}
