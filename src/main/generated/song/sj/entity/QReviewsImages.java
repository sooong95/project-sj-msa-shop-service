package song.sj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReviewsImages is a Querydsl query type for ReviewsImages
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewsImages extends EntityPathBase<ReviewsItemImages> {

    private static final long serialVersionUID = 372643046L;

    public static final QReviewsImages reviewsImages = new QReviewsImages("reviewsImages");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QReviewsImages(String variable) {
        super(ReviewsItemImages.class, forVariable(variable));
    }

    public QReviewsImages(Path<? extends ReviewsItemImages> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReviewsImages(PathMetadata metadata) {
        super(ReviewsItemImages.class, metadata);
    }

}

