package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
