package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.ReviewImages;

public interface ReviewImageRepository extends JpaRepository<ReviewImages, Long> {
}
