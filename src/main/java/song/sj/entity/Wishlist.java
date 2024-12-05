package song.sj.entity;

import jakarta.persistence.*;
import lombok.Getter;
import song.sj.TimeStamp;

@Entity
@Getter
public class Wishlist extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    public void addWishlist(Member member, Shop shop) {
        this.member = member;
        this.shop = shop;
        member.getWishlists().add(this);
        shop.getWishlists().add(this);
        member.wishlistCount();
        shop.totalWishlistCount();
    }
}
