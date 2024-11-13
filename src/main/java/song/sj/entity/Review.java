package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviews_id")
    private Long id;

    private String reviewTitle;
    private String content;
    private double grade = 0;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "orderShop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderShop orderShop;

    @OneToMany(mappedBy = "review")
    private List<ReviewImages> reviewImagesList = new ArrayList<>();

    @JoinColumn(name = "shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    public Review(String reviewTitle, String content, double grade) {
        this.reviewTitle = reviewTitle;
        this.content = content;
        this.grade = grade;
    }

    public void orderShopSetting(OrderShop orderShop) {
        this.orderShop = orderShop;
        orderShop.getReviewList().add(this);
    }

    public void addReviews(Member member, Shop shop) {
        this.member = member;
        member.getReviewList().add(this);
        member.reviewsCount();
        shop.getReviewList().add(this);
        this.shop = shop;
        shop.addReview(this);
    }

    public void addReviewImages(ReviewImages images) {
        if (Objects.nonNull(images)) {
            this.reviewImagesList.add(images);
            images.controllerReviewImages(this);
        }
    }

    public void deleteReviewImages(ReviewImages images) {
        if (Objects.nonNull(images)) {
            this.reviewImagesList.remove(images);
            images.controllerReviewImages(null);
        }
    }
}
