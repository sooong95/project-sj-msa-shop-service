package song.sj.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import song.sj.dto.shop.ShopSearchConditionDto;
import song.sj.entity.QShop;
import song.sj.entity.Shop;

import java.util.List;

import static song.sj.entity.QShop.shop;

@RequiredArgsConstructor
public class ShopQueryRepositoryImpl implements ShopQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Shop> listAll() {

        return jpaQueryFactory
                .selectFrom(shop)
                .fetch();
    }

    @Override
    public Page<Shop> shopSortCategories(ShopSearchConditionDto dto) {
        return null;
    }


}
