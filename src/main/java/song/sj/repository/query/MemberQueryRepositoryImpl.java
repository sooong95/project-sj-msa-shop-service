package song.sj.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import song.sj.entity.Member;
import song.sj.enums.Role;

import java.util.List;

import static song.sj.entity.QMember.member;

@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> findMembers() {

        return queryFactory
                .selectFrom(member)
                .where(member.role.eq(Role.ROLE_MEMBER))
                .fetch();
    }

    @Override
    public List<Member> findShopMembers() {

        return queryFactory
                .selectFrom(member)
                .where(member.role.eq(Role.ROLE_SHOP))
                .fetch();
    }
}
