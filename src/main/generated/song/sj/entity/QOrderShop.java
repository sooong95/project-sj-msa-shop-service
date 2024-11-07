package song.sj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderShop is a Querydsl query type for OrderShop
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderShop extends EntityPathBase<OrderShop> {

    private static final long serialVersionUID = 230105943L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderShop orderShop = new QOrderShop("orderShop");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QOrder order;

    public final QShop shop;

    public QOrderShop(String variable) {
        this(OrderShop.class, forVariable(variable), INITS);
    }

    public QOrderShop(Path<? extends OrderShop> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderShop(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderShop(PathMetadata metadata, PathInits inits) {
        this(OrderShop.class, metadata, inits);
    }

    public QOrderShop(Class<? extends OrderShop> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
        this.shop = inits.isInitialized("shop") ? new QShop(forProperty("shop"), inits.get("shop")) : null;
    }

}

