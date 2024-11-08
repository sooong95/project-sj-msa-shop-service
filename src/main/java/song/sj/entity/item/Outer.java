package song.sj.entity.item;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Outer extends Item{

    private String productClassification;

    public Outer(String productClassification) {
        this.productClassification = productClassification;
    }
}
