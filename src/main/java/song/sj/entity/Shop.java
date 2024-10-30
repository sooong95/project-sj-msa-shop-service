package song.sj.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import song.sj.TimeStamp;
import song.sj.enums.Role;

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
    private List<ItemImages> itemImages = new ArrayList<>();

    public void addImage(ItemImages images) {
        if (Objects.nonNull(images)) {
            this.itemImages.add(images);
            images.controlShop(this);
        }
    }

    public void removeImage(ItemImages images) {
        if (Objects.nonNull(images)) {
            this.itemImages.remove(images);
            images.controlShop(null);
        }
    }
}
