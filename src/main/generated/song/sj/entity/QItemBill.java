package song.sj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemBill is a Querydsl query type for ItemBill
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemBill extends EntityPathBase<ItemBill> {

    private static final long serialVersionUID = 227205991L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemBill itemBill = new QItemBill("itemBill");

    public final song.sj.QTimeStamp _super = new song.sj.QTimeStamp(this);

    public final QBill bill;

    public final NumberPath<Integer> cost = createNumber("cost", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final song.sj.entity.item.QItem item;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final StringPath repairDescription = createString("repairDescription");

    public QItemBill(String variable) {
        this(ItemBill.class, forVariable(variable), INITS);
    }

    public QItemBill(Path<? extends ItemBill> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemBill(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemBill(PathMetadata metadata, PathInits inits) {
        this(ItemBill.class, metadata, inits);
    }

    public QItemBill(Class<? extends ItemBill> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bill = inits.isInitialized("bill") ? new QBill(forProperty("bill"), inits.get("bill")) : null;
        this.item = inits.isInitialized("item") ? new song.sj.entity.item.QItem(forProperty("item"), inits.get("item")) : null;
    }

}

