package song.sj.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import song.sj.entity.QItem;
import song.sj.entity.item.Item;

import static song.sj.entity.QItem.item;

@RequiredArgsConstructor
public class ItemQueryRepositoryImpl implements ItemQueryRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Item findItem() {
        /*return queryFactory
                .selectFrom(item)
                .where()
                .fetchOne();*/

        return null;
    }
}
