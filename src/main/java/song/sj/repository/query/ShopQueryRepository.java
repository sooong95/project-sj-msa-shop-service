package song.sj.repository.query;

import song.sj.entity.Shop;

import java.util.List;

public interface ShopQueryRepository {

    List<Shop> shopSortCategories(String categoryName);
}
