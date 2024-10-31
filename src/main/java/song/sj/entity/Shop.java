package song.sj.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import song.sj.TimeStamp;

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

    private String description;

    @Embedded
    @Valid
    private Address address;

    @OneToMany(mappedBy = "shop")
    private List<ShopImages> shopImages = new ArrayList<>();

    public void addImage(ShopImages images) {
        if (Objects.nonNull(images)) {
            this.shopImages.add(images);
            images.controlShop(this);
        }
    }

    public void removeImage(ShopImages images) {
        if (Objects.nonNull(images)) {
            this.shopImages.remove(images);
            images.controlShop(null);
        }
    }
}
