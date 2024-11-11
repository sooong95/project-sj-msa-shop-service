package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.ShopCategory;

public interface ShopCategoryRepository extends JpaRepository<ShopCategory, Long> {

    ShopCategory findByShopCategoryName(String shopCategoryName);
}
