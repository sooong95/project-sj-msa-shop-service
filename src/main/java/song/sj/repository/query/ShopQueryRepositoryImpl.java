package song.sj.repository.query;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import song.sj.dto.shop.ShopSearchConditionDto;
import song.sj.entity.Shop;

import java.util.List;

import static song.sj.entity.QItemCategory.itemCategory;
import static song.sj.entity.QShop.shop;
import static song.sj.entity.QShopItemCategoryMiddleTable.shopItemCategoryMiddleTable;

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
    public Page<List<Shop>> shopSortCategories(ShopSearchConditionDto dto) {
        return jpaQueryFactory
                .selectFrom(shop)
                .leftJoin(shop.shopCategoryMiddleTableList, shopItemCategoryMiddleTable)
                .leftJoin(shopItemCategoryMiddleTable.itemCategory, itemCategory)
                .where(
                        itemCategoryNameEq(dto.getShopItemCategoryName())


                )
                .orderBy()
                .fetch();
    }

    private BooleanExpression itemCategoryNameEq(List<String> itemCategoryName) {
        return itemCategoryName == null || itemCategoryName.isEmpty() ? null : itemCategory.itemCategoryName.in(itemCategoryName);
    }

    /*private OrderSpecifier<?> sortOrder(String sortOrder) {
        if ("DESC".equalsIgnoreCase(sortOrder)) {
            return
        }*/
    }
}
