package song.sj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseImages is a Querydsl query type for BaseImages
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseImages extends EntityPathBase<BaseImages> {

    private static final long serialVersionUID = 982331158L;

    public static final QBaseImages baseImages = new QBaseImages("baseImages");

    public final song.sj.QTimeStamp _super = new song.sj.QTimeStamp(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath imageName = createString("imageName");

    public final StringPath imageType = createString("imageType");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath serverImageName = createString("serverImageName");

    public QBaseImages(String variable) {
        super(BaseImages.class, forVariable(variable));
    }

    public QBaseImages(Path<? extends BaseImages> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseImages(PathMetadata metadata) {
        super(BaseImages.class, metadata);
    }

}

