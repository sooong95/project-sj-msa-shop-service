package song.sj.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import song.sj.entity.ItemImages;
import song.sj.entity.QItemImages;
import song.sj.entity.item.QItem;

import java.util.List;

import static song.sj.entity.QItemImages.itemImages;
import static song.sj.entity.item.QItem.item;

@SpringBootTest
@Transactional
public class ItemRepositoryTest {

    JPAQueryFactory jpaQueryFactory;

    @Autowired
    ItemImageRepository itemImageRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    void before() {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    /*@BeforeEach
    void init() {
        itemImageRepository.save(new ItemImages("imageA", "sdjlf", ""))
    }*/

    @Test
    void findByItemId() {

        Long id = 13123L;

        List<ItemImages> images = jpaQueryFactory
                .selectFrom(itemImages)
                .leftJoin(itemImages.item, item)
                .where(itemImages.item.id.eq(id))
                .fetch();

        Assertions.assertThat(images.size()).isEqualTo(0);
    }
}
