package song.sj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemCategory is a Querydsl query type for ItemCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemCategory extends EntityPathBase<ItemCategory> {

    private static final long serialVersionUID = -1959736226L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemCategory itemCategory = new QItemCategory("itemCategory");

    public final song.sj.QTimeStamp _super = new song.sj.QTimeStamp(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath itemCategoryName = createString("itemCategoryName");

    public final ListPath<song.sj.entity.item.Item, song.sj.entity.item.QItem> items = this.<song.sj.entity.item.Item, song.sj.entity.item.QItem>createList("items", song.sj.entity.item.Item.class, song.sj.entity.item.QItem.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final QItemCategory parent;

    public final ListPath<ShopItemCategoryMiddleTable, QShopItemCategoryMiddleTable> shopItemCategoryMiddleTableList = this.<ShopItemCategoryMiddleTable, QShopItemCategoryMiddleTable>createList("shopItemCategoryMiddleTableList", ShopItemCategoryMiddleTable.class, QShopItemCategoryMiddleTable.class, PathInits.DIRECT2);

    public final ListPath<ItemCategory, QItemCategory> subCategory = this.<ItemCategory, QItemCategory>createList("subCategory", ItemCategory.class, QItemCategory.class, PathInits.DIRECT2);

    public QItemCategory(String variable) {
        this(ItemCategory.class, forVariable(variable), INITS);
    }

    public QItemCategory(Path<? extends ItemCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemCategory(PathMetadata metadata, PathInits inits) {
        this(ItemCategory.class, metadata, inits);
    }

    public QItemCategory(Class<? extends ItemCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QItemCategory(forProperty("parent"), inits.get("parent")) : null;
    }

}

