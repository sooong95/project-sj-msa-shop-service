package song.sj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReviews is a Querydsl query type for Reviews
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviews extends EntityPathBase<Reviews> {

    private static final long serialVersionUID = -2117273394L;

    public static final QReviews reviews = new QReviews("reviews");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QReviews(String variable) {
        super(Reviews.class, forVariable(variable));
    }

    public QReviews(Path<? extends Reviews> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReviews(PathMetadata metadata) {
        super(Reviews.class, metadata);
    }

}

