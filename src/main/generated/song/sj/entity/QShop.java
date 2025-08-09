package song.sj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShop is a Querydsl query type for Shop
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShop extends EntityPathBase<Shop> {

    private static final long serialVersionUID = 324487971L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShop shop = new QShop("shop");

    public final song.sj.QTimeStamp _super = new song.sj.QTimeStamp(this);

    public final QAddress address;

    public final NumberPath<Double> averageGrade = createNumber("averageGrade", Double.class);

    public final ListPath<Long, NumberPath<Long>> billIdList = this.<Long, NumberPath<Long>>createList("billIdList", Long.class, NumberPath.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<ShopItemCategory, QShopItemCategory> itemCategories = this.<ShopItemCategory, QShopItemCategory>createList("itemCategories", ShopItemCategory.class, QShopItemCategory.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final ListPath<String, StringPath> mainEvent = this.<String, StringPath>createList("mainEvent", String.class, StringPath.class, PathInits.DIRECT2);

    public final ListPath<Long, NumberPath<Long>> orderShopIdList = this.<Long, NumberPath<Long>>createList("orderShopIdList", Long.class, NumberPath.class, PathInits.DIRECT2);

    public final NumberPath<Long> ownerMemberId = createNumber("ownerMemberId", Long.class);

    public final ListPath<Long, NumberPath<Long>> reviewIdList = this.<Long, NumberPath<Long>>createList("reviewIdList", Long.class, NumberPath.class, PathInits.DIRECT2);

    public final StringPath shopDescription = createString("shopDescription");

    public final ListPath<ShopImages, QShopImages> shopImages = this.<ShopImages, QShopImages>createList("shopImages", ShopImages.class, QShopImages.class, PathInits.DIRECT2);

    public final StringPath shopName = createString("shopName");

    public final NumberPath<Double> totalGradeSum = createNumber("totalGradeSum", Double.class);

    public final NumberPath<Integer> totalReviewCount = createNumber("totalReviewCount", Integer.class);

    public final NumberPath<Integer> totalWishlistCount = createNumber("totalWishlistCount", Integer.class);

    public final ListPath<Long, NumberPath<Long>> wishlists = this.<Long, NumberPath<Long>>createList("wishlists", Long.class, NumberPath.class, PathInits.DIRECT2);

    public QShop(String variable) {
        this(Shop.class, forVariable(variable), INITS);
    }

    public QShop(Path<? extends Shop> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShop(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShop(PathMetadata metadata, PathInits inits) {
        this(Shop.class, metadata, inits);
    }

    public QShop(Class<? extends Shop> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
    }

}

