package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
