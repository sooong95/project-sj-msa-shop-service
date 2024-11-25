package song.sj.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import song.sj.entity.*;

import static song.sj.entity.QBill.bill;
import static song.sj.entity.QItemBill.itemBill;
import static song.sj.entity.QMember.member;
import static song.sj.entity.QShop.shop;

@RequiredArgsConstructor
public class BillQueryRepositoryImpl implements BillQueryRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Bill getBill(Long memberId) {

        return jpaQueryFactory
                .selectFrom(bill)
                .leftJoin(bill.shop, shop)
                .leftJoin(bill.member, member)
                .leftJoin(bill.itemBillList, itemBill)
                .where(member.id.eq(memberId))
                .fetchOne();
    }
}
