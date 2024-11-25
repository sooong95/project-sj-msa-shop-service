package song.sj.repository.query;

import song.sj.entity.Bill;

public interface BillQueryRepository {

    Bill getBill(Long memberId);
}
