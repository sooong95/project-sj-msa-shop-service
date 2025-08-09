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

    @Column(nullable = false)
    private Shop shop;
    @Column(nullable = false)
    private Long itemCategoryId;

    public void addShop(Shop shop) {
        this.shop = shop;
        shop.getItemCategories().add(this);
    }

    public void addItemCategory(Long itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }
}
