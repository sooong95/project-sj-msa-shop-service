package song.sj.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import song.sj.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "shop_orders")
public class ShopOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_order_id")
    private Long id;

    @JoinColumn(name = "shop_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    private Long orderId;
    private int count;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder.Default
    @OneToMany(mappedBy = "shopOrder")
    private List<ShopOrderItem> shopOrderItemList = new ArrayList<>();

    public void assignShopOrder(Shop shop) {
        this.shop = shop;
        shop.getShopOrderList().add(this);
    }

    public void changeOrderStatus(OrderStatus orderStatus) {
        if (Objects.nonNull(orderStatus)) {
            this.orderStatus = orderStatus;
        }
    }
}
