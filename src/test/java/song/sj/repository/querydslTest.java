package song.sj.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import song.sj.entity.Member;
import song.sj.entity.QMember;

@SpringBootTest
@Transactional
public class querydslTest {

    @Autowired
    EntityManager em;

    @Test
    public void querydslStart() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMember m = new QMember("m");

        Member findMember = queryFactory
                .select(m)
                .from(m)
                .where(m.username.eq("song1"))
                .fetchOne();

        Assertions.assertThat(findMember.getUsername()).isEqualTo("song1");
    }
}
