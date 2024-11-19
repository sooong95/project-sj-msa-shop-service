package song.sj.entity.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEtc is a Querydsl query type for Etc
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEtc extends EntityPathBase<Etc> {

    private static final long serialVersionUID = 161931686L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEtc etc = new QEtc("etc");

    public final QItem _super;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate;

    //inherited
    public final StringPath description;

    //inherited
    public final StringPath design;

    //inherited
    public final NumberPath<Long> id;

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

    public QEtc(String variable) {
        this(Etc.class, forVariable(variable), INITS);
    }

    public QEtc(Path<? extends Etc> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEtc(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEtc(PathMetadata metadata, PathInits inits) {
        this(Etc.class, metadata, inits);
    }

    public QEtc(Class<? extends Etc> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QItem(type, metadata, inits);
        this.createdDate = _super.createdDate;
        this.description = _super.description;
        this.design = _super.design;
        this.id = _super.id;
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

