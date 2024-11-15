package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import song.sj.TimeStamp;
import song.sj.enums.PaymentStatus;

@Slf4j
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    private int amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Payment(Member member, PaymentStatus paymentStatus, int amount) {
        this.member = member;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        member.getPaymentList().add(this);
    }

    public void deposit(int amount) {
        if (amount > 0) {
            this.member.inCreaseBalance(amount);
            this.paymentStatus = PaymentStatus.DEPOSIT;
        }
    }

    public void withdrawal(int amount) {
        if (amount > 0) {
            this.member.decreaseBalance(amount);
            this.paymentStatus = PaymentStatus.WITHDRAWAL;
        }
    }

    public void payment(int amount) {

    }
}
