package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
}
