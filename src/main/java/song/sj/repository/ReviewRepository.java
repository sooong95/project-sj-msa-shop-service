package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {


}
