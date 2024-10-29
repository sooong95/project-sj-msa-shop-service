package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.item.Item;
import song.sj.repository.query.ItemQueryRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemQueryRepository {
}
