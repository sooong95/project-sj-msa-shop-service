package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.Shop;
import song.sj.repository.query.ShopQueryRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long>, ShopQueryRepository {

    Optional<Shop> findByShopName(String shopName);
}
