package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import song.sj.TimeStamp;
import song.sj.entity.item.Item;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemImages extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_images_id")
    private Long id;

    private String imageName;

    private String serverImageName;
    private String imageType;
    @Lob
    private byte[] images;

    @Builder
    public ItemImages(String imageName, String serverImageName, String imageType, byte[] images) {
        this.imageName = imageName;
        this.serverImageName = serverImageName;
        this.imageType = imageType;
        this.images = images;
    }

    @JoinColumn(name = "item_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    public void controlItem(Item item) {
        if (Objects.nonNull(item)) this.item = item;
    }

}
