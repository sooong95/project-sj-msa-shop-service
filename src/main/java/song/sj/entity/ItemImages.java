package song.sj.entity;

import jakarta.persistence.*;
import lombok.*;
import song.sj.TimeStamp;
import song.sj.entity.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemImages extends BaseImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_images_id")
    private Long id;

    @Builder
    public ItemImages(String imageName, String serverImageName, String imageType) {
        super(imageName, serverImageName, imageType);
    }

    @JoinColumn(name = "item_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    public void controlItem(Item item) {
        if (Objects.nonNull(item)) this.item = item;
    }
}
