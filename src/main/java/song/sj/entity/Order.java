package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import song.sj.TimeStamp;
import song.sj.entity.item.Item;
import song.sj.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<Item> itemList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private List<OrderShop> orderShopList = new ArrayList<>();

    public void addOrderShop(Shop shop) {
        this.orderShopList.add(OrderShop.createOrderShop(shop, this));
    }

    public Order createOrder(Member member, List<Item> items, List<Shop> shopList) {

        Order order = new Order();
        order.member = member;

        for (Item item : items) {
            order.getItemList().add(item);
            item.addOrder(this);
        }

        for (Shop shop : shopList) {
            addOrderShop(shop);
        }

        this.orderStatus = OrderStatus.ORDER;

        return order;
    }
}
