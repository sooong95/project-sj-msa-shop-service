package song.sj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBill is a Querydsl query type for Bill
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBill extends EntityPathBase<Bill> {

    private static final long serialVersionUID = 323982388L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBill bill = new QBill("bill");

    public final song.sj.QTimeStamp _super = new song.sj.QTimeStamp(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<ItemBill, QItemBill> itemBillList = this.<ItemBill, QItemBill>createList("itemBillList", ItemBill.class, QItemBill.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final QMember member;

    public final QOrder order;

    public final QShop shop;

    public final NumberPath<Integer> totalCost = createNumber("totalCost", Integer.class);

    public QBill(String variable) {
        this(Bill.class, forVariable(variable), INITS);
    }

    public QBill(Path<? extends Bill> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBill(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBill(PathMetadata metadata, PathInits inits) {
        this(Bill.class, metadata, inits);
    }

    public QBill(Class<? extends Bill> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
        this.shop = inits.isInitialized("shop") ? new QShop(forProperty("shop"), inits.get("shop")) : null;
    }

}

