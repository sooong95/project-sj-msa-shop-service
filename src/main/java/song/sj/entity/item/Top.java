package song.sj.entity.item;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Top extends Item {

    private String productClassification;

    public Top(String productClassification) {
        this.productClassification = productClassification;
    }
}
