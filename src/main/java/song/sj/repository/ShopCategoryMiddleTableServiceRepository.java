package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.ShopCategoryMiddleTable;

public interface ShopCategoryMiddleTableServiceRepository extends JpaRepository<ShopCategoryMiddleTable, Long> {
}
