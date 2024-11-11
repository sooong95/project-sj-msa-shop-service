package song.sj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShopCategoryMiddleTable is a Querydsl query type for ShopCategoryMiddleTable
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShopCategoryMiddleTable extends EntityPathBase<ShopCategoryMiddleTable> {

    private static final long serialVersionUID = 1111397176L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShopCategoryMiddleTable shopCategoryMiddleTable = new QShopCategoryMiddleTable("shopCategoryMiddleTable");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QShop shop;

    public final QShopCategory shopCategory;

    public QShopCategoryMiddleTable(String variable) {
        this(ShopCategoryMiddleTable.class, forVariable(variable), INITS);
    }

    public QShopCategoryMiddleTable(Path<? extends ShopCategoryMiddleTable> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShopCategoryMiddleTable(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShopCategoryMiddleTable(PathMetadata metadata, PathInits inits) {
        this(ShopCategoryMiddleTable.class, metadata, inits);
    }

    public QShopCategoryMiddleTable(Class<? extends ShopCategoryMiddleTable> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shop = inits.isInitialized("shop") ? new QShop(forProperty("shop"), inits.get("shop")) : null;
        this.shopCategory = inits.isInitialized("shopCategory") ? new QShopCategory(forProperty("shopCategory"), inits.get("shopCategory")) : null;
    }

}

