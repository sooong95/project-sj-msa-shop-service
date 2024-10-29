package song.sj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemImages is a Querydsl query type for ItemImages
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemImages extends EntityPathBase<ItemImages> {

    private static final long serialVersionUID = -494605864L;

    public static final QItemImages itemImages = new QItemImages("itemImages");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QItemImages(String variable) {
        super(ItemImages.class, forVariable(variable));
    }

    public QItemImages(Path<? extends ItemImages> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemImages(PathMetadata metadata) {
        super(ItemImages.class, metadata);
    }

}

