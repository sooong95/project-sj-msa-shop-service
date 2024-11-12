package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewsItemImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviews_images_id")
    private Long id;

    @JoinColumn(name = "reviews_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Reviews reviews;

    @JoinColumn(name = "ItemImages_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ItemImages itemImages;

    public void reviewsSetting(Reviews reviews) {
        this.reviews = reviews;
        reviews.getReviewsItemImagesList().add(this);
    }

    public void itemImagesSetting(ItemImages itemImages) {
        this.itemImages = itemImages;
        itemImages.getReviewsItemImagesList().add(this);
    }
}
