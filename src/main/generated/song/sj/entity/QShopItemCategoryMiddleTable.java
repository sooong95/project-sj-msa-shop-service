package song.sj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShopItemCategoryMiddleTable is a Querydsl query type for ShopItemCategoryMiddleTable
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShopItemCategoryMiddleTable extends EntityPathBase<ShopItemCategoryMiddleTable> {

    private static final long serialVersionUID = -18828635L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShopItemCategoryMiddleTable shopItemCategoryMiddleTable = new QShopItemCategoryMiddleTable("shopItemCategoryMiddleTable");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QItemCategory itemCategory;

    public final QShop shop;

    public QShopItemCategoryMiddleTable(String variable) {
        this(ShopItemCategoryMiddleTable.class, forVariable(variable), INITS);
    }

    public QShopItemCategoryMiddleTable(Path<? extends ShopItemCategoryMiddleTable> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShopItemCategoryMiddleTable(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShopItemCategoryMiddleTable(PathMetadata metadata, PathInits inits) {
        this(ShopItemCategoryMiddleTable.class, metadata, inits);
    }

    public QShopItemCategoryMiddleTable(Class<? extends ShopItemCategoryMiddleTable> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.itemCategory = inits.isInitialized("itemCategory") ? new QItemCategory(forProperty("itemCategory"), inits.get("itemCategory")) : null;
        this.shop = inits.isInitialized("shop") ? new QShop(forProperty("shop"), inits.get("shop")) : null;
    }

}

