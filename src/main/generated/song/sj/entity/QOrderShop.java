package song.sj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderShop is a Querydsl query type for OrderShop
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderShop extends EntityPathBase<OrderShop> {

    private static final long serialVersionUID = 230105943L;

    public static final QOrderShop orderShop = new QOrderShop("orderShop");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QOrderShop(String variable) {
        super(OrderShop.class, forVariable(variable));
    }

    public QOrderShop(Path<? extends OrderShop> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderShop(PathMetadata metadata) {
        super(OrderShop.class, metadata);
    }

}

