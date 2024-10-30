package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import song.sj.entity.item.Item;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopImages {

    @Builder
    public ShopImages(String imageName, String imageType, byte[] images) {
        this.imageName = imageName;
        this.imageType = imageType;
        this.images = images;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_images_id")
    private Long id;

    private String imageName;
    private String imageType;

    @Lob
    private byte[] images;

    @JoinColumn(name = "shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    public void controlShop(Shop shop) {
        if (Objects.nonNull(shop)) this.shop = shop;
    }
}
