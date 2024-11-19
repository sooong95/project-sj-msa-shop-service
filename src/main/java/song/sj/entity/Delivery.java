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

    private String courierName;
    private String memo;

    @Embedded
    private Address address;

    private LocalDateTime deliveryStartDate;
    private LocalDateTime deliveredDate;

    public void orderSetting(Order order) {
        this.order = order;
    }

    public void memberSetting(Member member) {
        this.member = member;
    }



    public void changeDeliveryStatus(DeliveryStatus deliveryStatus) {
        if (Objects.nonNull(deliveryStatus)) this.deliveryStatus = deliveryStatus;
    }
}
