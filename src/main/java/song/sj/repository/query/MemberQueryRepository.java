package song.sj.repository.query;

import song.sj.entity.Member;

import java.util.List;

public interface MemberQueryRepository {

    List<Member> findMembers();

    List<Member> findShopMembers();
}
