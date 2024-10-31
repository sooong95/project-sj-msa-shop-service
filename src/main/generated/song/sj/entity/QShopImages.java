package song.sj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShopImages is a Querydsl query type for ShopImages
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShopImages extends EntityPathBase<ShopImages> {

    private static final long serialVersionUID = 665621371L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShopImages shopImages = new QShopImages("shopImages");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageName = createString("imageName");

    public final ArrayPath<byte[], Byte> images = createArray("images", byte[].class);

    public final StringPath imageType = createString("imageType");

    public final QShop shop;

    public QShopImages(String variable) {
        this(ShopImages.class, forVariable(variable), INITS);
    }

    public QShopImages(Path<? extends ShopImages> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShopImages(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShopImages(PathMetadata metadata, PathInits inits) {
        this(ShopImages.class, metadata, inits);
    }

    public QShopImages(Class<? extends ShopImages> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shop = inits.isInitialized("shop") ? new QShop(forProperty("shop"), inits.get("shop")) : null;
    }

}

