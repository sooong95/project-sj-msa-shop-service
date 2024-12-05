package song.sj.entity.item;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bottom extends Item {

    private String productClassification;

    public Bottom(String productClassification) {
        this.productClassification = productClassification;
    }
}
