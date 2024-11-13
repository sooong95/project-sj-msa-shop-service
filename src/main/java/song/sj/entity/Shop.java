package song.sj.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.util.StringUtils;
import song.sj.TimeStamp;
import song.sj.enums.ItemValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop extends TimeStamp {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "shop_id")
    private Long id;

    private String shopName;
    private String shopDescription;
    @Enumerated(EnumType.STRING)
    private List<ItemValue> mainEvent;

    private int totalReviewsCount = 0;
    private int totalWishlistCount = 0;
    private double averageGrade = 0.0;

    @Transient
    private double totalGradeSum = 0.0;

    @Embedded
    @Valid
    private Address address;

    @OneToMany(mappedBy = "shop")
    private List<ShopImages> shopImages = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<OrderShop> orderShopList = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<ShopCategoryMiddleTable> shopCategoryMiddleTableList = new ArrayList<>();

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "shop")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<Wishlist> wishlists = new ArrayList<>();

    public Shop(String shopName, String shopDescription, List<ItemValue> mainEvent, Address address) {
        this.shopName = shopName;
        this.shopDescription = shopDescription;
        this.mainEvent = mainEvent;
        this.address = address;
    }

    public void changeShopName(String shopName) {
        if (StringUtils.hasText(shopName)) this.shopName = shopName;
    }

    public void changeShopDescription(String shopDescription) {
        if (StringUtils.hasText(shopDescription)) this.shopDescription = shopDescription;
    }

    public void changeMainEvent(List<ItemValue> mainEvent) {
        if (Objects.nonNull(mainEvent)) this.mainEvent = mainEvent;
    }

    public void changeAddress(Address address) {
        if (Objects.nonNull(address)) this.address = address;
    }

    public void addImage(ShopImages images) {
        if (Objects.nonNull(images)) {
            this.shopImages.add(images);
            images.controlShop(this);
        }
    }

    public void deleteImage(ShopImages images) {
        if (Objects.nonNull(images)) {
            this.shopImages.remove(images);
            images.controlShop(null);
        }
    }

    public void addShop(Member member) {
        if (Objects.nonNull(member)) {
            member.getShopList().add(this);
            this.member = member;
        }
    }

    public void addReview(Review review) {
        if (Objects.nonNull(review)) {
            totalReviewsCount++;
            totalGradeSum += review.getGrade();
            calculateAverageGrade();
        }
    }

    public void calculateAverageGrade() {
        if (totalReviewsCount > 0) this.averageGrade = totalGradeSum / totalReviewsCount;
    }

    public void totalWishlistCount() {
        totalWishlistCount++;
    }

    public void averageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }
}
