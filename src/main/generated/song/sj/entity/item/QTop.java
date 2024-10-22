package song.sj.entity.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTop is a Querydsl query type for Top
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTop extends EntityPathBase<Top> {

    private static final long serialVersionUID = 161945959L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTop top = new QTop("top");

    public final QItem _super;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate;

    //inherited
    public final StringPath description;

    //inherited
    public final NumberPath<Long> id;

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

    public QTop(String variable) {
        this(Top.class, forVariable(variable), INITS);
    }

    public QTop(Path<? extends Top> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTop(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTop(PathMetadata metadata, PathInits inits) {
        this(Top.class, metadata, inits);
    }

    public QTop(Class<? extends Top> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QItem(type, metadata, inits);
        this.createdDate = _super.createdDate;
        this.description = _super.description;
        this.id = _super.id;
        this.lastModifiedDate = _super.lastModifiedDate;
        this.material = _super.material;
        this.member = _super.member;
        this.order = _super.order;
        this.size = _super.size;
    }

}

