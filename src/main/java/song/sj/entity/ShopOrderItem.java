package song.sj.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "shop_order_item")
public class ShopOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_order_item_id")
    private Long id;

    @JoinColumn(name = "shop_order_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ShopOrder shopOrder;

    private Long itemId;
    private String itemName;
    private String description;
    private int itemQuantity;

    @Builder.Default
    private List<String> itemImageUrlList = new ArrayList<>();

    public void assignShopOrderItem(ShopOrder shopOrder) {
        this.shopOrder = shopOrder;
        shopOrder.getShopOrderItemList().add(this);
    }
}
