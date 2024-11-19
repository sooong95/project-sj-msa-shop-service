package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
