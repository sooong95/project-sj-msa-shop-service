package song.sj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1874287737L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final song.sj.QTimeStamp _super = new song.sj.QTimeStamp(this);

    public final QAddress address;

    public final NumberPath<Integer> balance = createNumber("balance", Integer.class);

    public final StringPath businessRegistrationNumber = createString("businessRegistrationNumber");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<song.sj.entity.item.Item, song.sj.entity.item.QItem> items = this.<song.sj.entity.item.Item, song.sj.entity.item.QItem>createList("items", song.sj.entity.item.Item.class, song.sj.entity.item.QItem.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath password = createString("password");

    public final ListPath<Payment, QPayment> paymentList = this.<Payment, QPayment>createList("paymentList", Payment.class, QPayment.class, PathInits.DIRECT2);

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final NumberPath<Integer> reviewCount = createNumber("reviewCount", Integer.class);

    public final ListPath<Review, QReview> reviewList = this.<Review, QReview>createList("reviewList", Review.class, QReview.class, PathInits.DIRECT2);

    public final EnumPath<song.sj.enums.Role> role = createEnum("role", song.sj.enums.Role.class);

    public final ListPath<Shop, QShop> shopList = this.<Shop, QShop>createList("shopList", Shop.class, QShop.class, PathInits.DIRECT2);

    public final StringPath shopName = createString("shopName");

    public final StringPath username = createString("username");

    public final NumberPath<Integer> wishlistCount = createNumber("wishlistCount", Integer.class);

    public final ListPath<Wishlist, QWishlist> wishlists = this.<Wishlist, QWishlist>createList("wishlists", Wishlist.class, QWishlist.class, PathInits.DIRECT2);

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
    }

}

