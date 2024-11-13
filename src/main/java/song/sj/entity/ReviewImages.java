package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewImages extends BaseImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_images_id")
    private Long id;

    public ReviewImages(String imageName, String serverImageName, String imageType) {
        super(imageName, serverImageName, imageType);
    }

    @JoinColumn(name = "review_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;

    public void controllerReviewImages(Review review) {
        if (Objects.nonNull(review)) this.review = review;
    }
}
