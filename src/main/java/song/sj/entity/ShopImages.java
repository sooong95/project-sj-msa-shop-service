package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopImages extends BaseImages{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_images_id")
    private Long id;

    @Builder
    public ShopImages(String imageName, String serverImageName, String imageType) {
        super(imageName, serverImageName, imageType);
    }

    @JoinColumn(name = "shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    public void controlShop(Shop shop) {
        if (Objects.nonNull(shop)) this.shop = shop;
    }
}
