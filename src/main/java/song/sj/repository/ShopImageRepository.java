package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.ShopImages;

import java.util.Optional;

public interface ShopImageRepository extends JpaRepository<ShopImages, Long> {

    Optional<ShopImages> findByImageName(String imageName);
}
