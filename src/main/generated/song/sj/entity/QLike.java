package song.sj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLike is a Querydsl query type for Like
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLike extends EntityPathBase<Wishlist> {

    private static final long serialVersionUID = 324280260L;

    public static final QLike like = new QLike("like1");

    public final song.sj.QTimeStamp _super = new song.sj.QTimeStamp(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public QLike(String variable) {
        super(Wishlist.class, forVariable(variable));
    }

    public QLike(Path<? extends Wishlist> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLike(PathMetadata metadata) {
        super(Wishlist.class, metadata);
    }

}

