package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.ShopOrderItem;

public interface ShopOrderItemRepository extends JpaRepository<ShopOrderItem, Long> {
}
