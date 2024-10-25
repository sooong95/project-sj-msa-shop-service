package song.sj.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import song.sj.entity.Member;
import song.sj.entity.QMember;

import static song.sj.entity.QMember.member;

@SpringBootTest
@Transactional
public class querydslTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    void before() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    public void querydslStart() {
        QMember m = new QMember("m");

        Member findMember = queryFactory
                .select(m)
                .from(m)
                .where(m.username.eq("song1"))
                .fetchOne();

        Assertions.assertThat(findMember.getUsername()).isEqualTo("song1");
    }

    @Test
    void search() {

        Member findMember = queryFactory
                .selectFrom(QMember.member)
                .where(QMember.member.username.eq("song1").and(QMember.member.email.eq("song1@nnnn.com")))
                .fetchOne();

        Assertions.assertThat(findMember.getUsername()).isEqualTo("song1");
        Assertions.assertThat(findMember.getEmail()).isEqualTo("song1@nnnn.com");
    }

    @Test
    void result() {

        queryFactory
                .select(member.count())
                .from(member)
                .fetchOne();
    }
}
