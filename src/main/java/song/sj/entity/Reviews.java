package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviews_id")
    private Long id;

    private String reviewTitle;
    private String content;
    private double grade;
    private int reviewsCount = 0;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "reviews")
    private List<ReviewsItemImages> reviewsItemImagesList = new ArrayList<>();

    @JoinColumn(name = "orderShop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderShop orderShop;



    public void orderShopSetting(OrderShop orderShop) {
        this.orderShop = orderShop;
        orderShop.getReviewsList().add(this);
    }

    public void addReviews(Member member) {
        this.member = member;
        member.getReviewsList().add(this);
        reviewsCount++;
    }
}
