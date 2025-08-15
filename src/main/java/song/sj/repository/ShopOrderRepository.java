package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.ShopOrder;

public interface ShopOrderRepository extends JpaRepository<ShopOrder, Long> {
}
