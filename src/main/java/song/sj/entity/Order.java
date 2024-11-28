package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import song.sj.TimeStamp;
import song.sj.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
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

    @JoinColumn(name = "delivery_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Delivery delivery;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private List<OrderShop> orderShopList = new ArrayList<>();

    @OneToMany(mappedBy = "order")
    private List<Bill> billList = new ArrayList<>();

    public static void addOrderShop(Shop shop) {
        Order order = new Order();
        OrderShop orderShop = new OrderShop();
        orderShop.addOrder(order);
    }

    public static Order setMemberAndOrderStatus(Member member) {

        Order order = new Order();
        order.member = member;

        order.orderStatus = OrderStatus.ORDER;

        return order;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public void changeOrderStatus(OrderStatus orderStatus) {
        if (Objects.nonNull(orderStatus)) this.orderStatus = orderStatus;
    }
}
