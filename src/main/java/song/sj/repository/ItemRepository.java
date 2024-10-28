package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
