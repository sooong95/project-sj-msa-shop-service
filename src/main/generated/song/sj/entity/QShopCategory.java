package song.sj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShopCategory is a Querydsl query type for ShopCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShopCategory extends EntityPathBase<ShopCategory> {

    private static final long serialVersionUID = 622106945L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShopCategory shopCategory = new QShopCategory("shopCategory");

    public final song.sj.QTimeStamp _super = new song.sj.QTimeStamp(this);

    public final StringPath categoryName = createString("categoryName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<song.sj.entity.item.Item, song.sj.entity.item.QItem> items = this.<song.sj.entity.item.Item, song.sj.entity.item.QItem>createList("items", song.sj.entity.item.Item.class, song.sj.entity.item.QItem.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final QShopCategory parent;

    public QShopCategory(String variable) {
        this(ShopCategory.class, forVariable(variable), INITS);
    }

    public QShopCategory(Path<? extends ShopCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShopCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShopCategory(PathMetadata metadata, PathInits inits) {
        this(ShopCategory.class, metadata, inits);
    }

    public QShopCategory(Class<? extends ShopCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QShopCategory(forProperty("parent"), inits.get("parent")) : null;
    }

}

