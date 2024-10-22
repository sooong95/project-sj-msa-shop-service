package song.sj.entity.item.bottom;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import song.sj.entity.item.Bottom;


/**
 * QBottom is a Querydsl query type for Bottom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBottom extends EntityPathBase<Bottom> {

    private static final long serialVersionUID = 792827168L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBottom bottom = new QBottom("bottom");

    public final song.sj.entity.item.QItem _super;

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
        this._super = new song.sj.entity.item.QItem(type, metadata, inits);
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

