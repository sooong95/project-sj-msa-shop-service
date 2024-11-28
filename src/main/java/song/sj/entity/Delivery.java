package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import song.sj.enums.DeliveryStatus;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    private String courierName;
    private Long transportationNumber;
    private String memo;

    @Embedded
    private Address address;

    private LocalDateTime deliveryStartDate;
    private LocalDateTime deliveredDate;

    public Delivery(String courierName, Long transportationNumber, String memo, Address address) {
        this.courierName = courierName;
        this.transportationNumber = transportationNumber;
        this.memo = memo;
        this.address = address;
    }

    public void orderSetting(Order order) {
        this.order = order;
        order.setDelivery(this);
    }

    public void memberSetting(Member member) {
        this.member = member;
    }

    public void shopSetting(Shop shop) {
        this.shop = shop;
    }

    public void changeDeliveryStatus(DeliveryStatus deliveryStatus) {
        if (Objects.nonNull(deliveryStatus)) this.deliveryStatus = deliveryStatus;
    }

    public void setStartDate(LocalDateTime localDateTime) {
        this.deliveryStartDate = localDateTime;
    }

    public void setDeliveredDate(LocalDateTime localDateTime) {
        this.deliveredDate = localDateTime;
    }
}
