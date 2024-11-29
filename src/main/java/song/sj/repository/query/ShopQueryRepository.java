package song.sj.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import song.sj.dto.shop.ShopSearchConditionDto;
import song.sj.entity.Shop;

import java.util.List;

public interface ShopQueryRepository {

    List<Shop> listAll();

    Page<Shop> ShopConditionSearchList(ShopSearchConditionDto dto, Pageable pageable);
}
