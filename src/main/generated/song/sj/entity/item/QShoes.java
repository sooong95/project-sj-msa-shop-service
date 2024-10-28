package song.sj.entity.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShoes is a Querydsl query type for Shoes
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShoes extends EntityPathBase<Shoes> {

    private static final long serialVersionUID = 1010114170L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShoes shoes = new QShoes("shoes");

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
    public final StringPath itemName;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate;

    //inherited
    public final StringPath material;

    // inherited
    public final song.sj.entity.QMember member;

    // inherited
    public final song.sj.entity.QOrder order;

    public final StringPath productClassification = createString("productClassification");

    //inherited
    public final NumberPath<Integer> size;

    public QShoes(String variable) {
        this(Shoes.class, forVariable(variable), INITS);
    }

    public QShoes(Path<? extends Shoes> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShoes(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShoes(PathMetadata metadata, PathInits inits) {
        this(Shoes.class, metadata, inits);
    }

    public QShoes(Class<? extends Shoes> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QItem(type, metadata, inits);
        this.createdDate = _super.createdDate;
        this.description = _super.description;
        this.design = _super.design;
        this.id = _super.id;
        this.itemName = _super.itemName;
        this.lastModifiedDate = _super.lastModifiedDate;
        this.material = _super.material;
        this.member = _super.member;
        this.order = _super.order;
        this.size = _super.size;
    }

}

