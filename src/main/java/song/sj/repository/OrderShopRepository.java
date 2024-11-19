package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.OrderShop;

public interface OrderShopRepository extends JpaRepository<OrderShop, Long> {
}
