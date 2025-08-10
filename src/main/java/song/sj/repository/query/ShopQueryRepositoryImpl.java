package song.sj.repository.query;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import song.sj.dto.shop.ShopSearchConditionDto;
import song.sj.entity.Shop;

import java.util.List;
import java.util.Map;

import static song.sj.entity.QShop.shop;
import static song.sj.entity.QShopImages.shopImages;

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
    public Page<Shop> ShopConditionSearchList(ShopSearchConditionDto dto, Pageable pageable) {
        List<Shop> shopList = jpaQueryFactory
                .selectFrom(shop)
                .leftJoin(shop.shopItemCategoryList)
                /*.leftJoin(shopItemCategoryMiddleTable.itemCategory, itemCategory)*/
                .leftJoin(shop.shopImages, shopImages)
                .where(
                        /*itemCategoryNameEq(dto.getItemCategoryName()),*/
                        gradeGte(dto.getGrade()),
                        reviewsGte(dto.getReviewsCount()),
                        wishlistCount(dto.getWishlistCount())
                )
                .orderBy(getOrderSpecifier(dto.getSortField(), dto.getSortOrder()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(shop.count())
                .from(shop)
                .leftJoin(shop.shopItemCategoryList)
                .where(
                        /*itemCategoryNameEq(dto.getItemCategoryName()),*/
                        gradeGte(dto.getGrade()),
                        reviewsGte(dto.getReviewsCount()),
                        wishlistCount(dto.getWishlistCount())
                )
                .orderBy(getOrderSpecifier(dto.getSortField(), dto.getSortOrder()));

        return PageableExecutionUtils.getPage(shopList, pageable, countQuery::fetchOne);
    }

    /*private BooleanExpression itemCategoryNameEq(List<String> itemCategoryName) {
        return itemCategoryName == null || itemCategoryName.isEmpty() ? null : itemCategory.itemCategoryName.in(itemCategoryName);
    }*/

    private BooleanExpression gradeGte(double grade) {
        return grade > 0 ? shop.averageGrade.goe(grade) : null;
    }

    private BooleanExpression reviewsGte(int reviewsCount) {
        return reviewsCount > 0 ? shop.totalReviewCount.goe(reviewsCount) : null;
    }

    private BooleanExpression wishlistCount(int wishlistCount) {
        return wishlistCount > 0 ? shop.totalWishlistCount.goe(wishlistCount) : null;
    }

    private OrderSpecifier<?> getOrderSpecifier(String sortField, String sortOrder) {

        Map<String, ComparableExpressionBase<?>> fieldMapping = Map.of(
                "grade", shop.averageGrade,
                "reviewsCount", shop.totalReviewCount,
                "wishlistCount", shop.totalWishlistCount
        );

        ComparableExpressionBase<?> field = fieldMapping.getOrDefault(sortField.toLowerCase(), shop.id);

        return "desc".equalsIgnoreCase(sortOrder) ? field.desc() : field.asc();
    }
}
