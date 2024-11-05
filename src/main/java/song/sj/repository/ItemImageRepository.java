package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.ItemImages;
import song.sj.repository.query.ItemImageQueryRepository;

import java.util.Optional;

public interface ItemImageRepository extends JpaRepository<ItemImages, Long>, ItemImageQueryRepository {

    Optional<ItemImages> findByImageName(String imageName);

    Optional<ItemImages> findById(Long id);
}
