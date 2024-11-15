package song.sj.dto.payment;

import lombok.Data;
import song.sj.enums.PaymentStatus;

import java.time.LocalDateTime;

@Data
public class PaymentHistory {

    private int amount;
    private int balance;
    private PaymentStatus paymentStatus;
    private LocalDateTime processingTime;
}
