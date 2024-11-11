package song.sj.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import song.sj.entity.ItemImages;
import song.sj.entity.QItemImages;

import java.util.List;

import static song.sj.entity.QItemImages.itemImages;
import static song.sj.entity.item.QItem.item;


@Slf4j
@RequiredArgsConstructor
public class ItemImageQueryRepositoryImpl implements ItemImageQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ItemImages> findByItemId(Long id) {

        return jpaQueryFactory
                .selectFrom(itemImages)
                .leftJoin(itemImages.item, item)
                .where(itemImages.item.id.eq(id))
                .fetch();
    }
}
