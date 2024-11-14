package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

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

    public void addReview(Member member, Shop shop) {
        this.member = member;
        member.getReviewList().add(this);
        member.reviewCount();
        shop.getReviewList().add(this);
        this.shop = shop;
        shop.addReview(this);
    }

    public void deleteReview(Member member, Shop shop) {
        this.member = null;
        member.getReviewList().remove(this);
        member.reviewCountReduce();
        shop.getReviewList().remove(this);
        this.shop = null;
        shop.deleteReview(this);
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

    public void changeReviewTitle(String reviewTitle) {
        if (StringUtils.hasText(reviewTitle)) this.reviewTitle = reviewTitle;
    }

    public void changeContent(String content) {
        if (StringUtils.hasText(content)) this.content = content;
    }

    public void changeGrade(double grade) {
        if (grade > 0) this.grade = grade;
    }
}
