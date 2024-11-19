package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
