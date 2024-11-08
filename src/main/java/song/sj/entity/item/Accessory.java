package song.sj.entity.item;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Accessory extends Item{

    private String productClassification;

    public Accessory(String productClassification) {
        this.productClassification = productClassification;
    }
}
