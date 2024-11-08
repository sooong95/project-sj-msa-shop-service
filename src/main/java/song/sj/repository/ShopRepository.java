package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    Shop findByEmail(String email);
}
