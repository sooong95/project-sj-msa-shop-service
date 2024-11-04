package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.ItemImages;

import java.util.Optional;

public interface ItemImageRepository extends JpaRepository<ItemImages, Long> {

    Optional<ItemImages> findByImageName(String imageName);

    Optional<ItemImages> findById(Long id);
}
