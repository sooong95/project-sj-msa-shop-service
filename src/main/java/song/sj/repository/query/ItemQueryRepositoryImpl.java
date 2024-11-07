package song.sj.repository.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import song.sj.dto.item.ItemSearchConditionDto;
import song.sj.entity.QCategory;
import song.sj.entity.item.Item;
import song.sj.entity.item.QItem;
import song.sj.enums.ItemValue;

import java.util.List;

import static song.sj.entity.QCategory.category;
import static song.sj.entity.item.QItem.item;


@RequiredArgsConstructor
public class ItemQueryRepositoryImpl implements ItemQueryRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Item> itemSearch(ItemSearchConditionDto dto) {

        return queryFactory
                .selectFrom(item)
                .leftJoin(item.category, category)
                .where(
                        itemNameEq(dto.getItemName()),
                        categoryNameEq(dto.getCategoryName())
                )
                .fetch();
    }

    private BooleanExpression itemNameEq(String itemName) {
        return itemName.isBlank() ? null : item.itemName.eq(itemName);
    }

    private BooleanExpression categoryNameEq(String categoryName) {
        return categoryName.isBlank() ? null : category.categoryName.eq(categoryName);
    }
}
