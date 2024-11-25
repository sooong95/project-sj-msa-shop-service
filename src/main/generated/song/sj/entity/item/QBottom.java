package song.sj.entity.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBottom is a Querydsl query type for Bottom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBottom extends EntityPathBase<Bottom> {

    private static final long serialVersionUID = 768700633L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBottom bottom = new QBottom("bottom");

    public final QItem _super;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate;

    //inherited
    public final StringPath description;

    //inherited
    public final StringPath design;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final ListPath<song.sj.entity.ItemBill, song.sj.entity.QItemBill> itemBillList;

    // inherited
    public final song.sj.entity.QItemCategory itemCategory;

    //inherited
    public final ListPath<song.sj.entity.ItemImages, song.sj.entity.QItemImages> itemImages;

    //inherited
    public final StringPath itemName;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate;

    //inherited
    public final StringPath material;

    // inherited
    public final song.sj.entity.QMember member;

    // inherited
    public final song.sj.entity.QOrderItem orderItem;

    public final StringPath productClassification = createString("productClassification");

    //inherited
    public final NumberPath<Integer> quantity;

    //inherited
    public final NumberPath<Integer> size;

    public QBottom(String variable) {
        this(Bottom.class, forVariable(variable), INITS);
    }

    public QBottom(Path<? extends Bottom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBottom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBottom(PathMetadata metadata, PathInits inits) {
        this(Bottom.class, metadata, inits);
    }

    public QBottom(Class<? extends Bottom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QItem(type, metadata, inits);
        this.createdDate = _super.createdDate;
        this.description = _super.description;
        this.design = _super.design;
        this.id = _super.id;
        this.itemBillList = _super.itemBillList;
        this.itemCategory = _super.itemCategory;
        this.itemImages = _super.itemImages;
        this.itemName = _super.itemName;
        this.lastModifiedDate = _super.lastModifiedDate;
        this.material = _super.material;
        this.member = _super.member;
        this.orderItem = _super.orderItem;
        this.quantity = _super.quantity;
        this.size = _super.size;
    }

}

