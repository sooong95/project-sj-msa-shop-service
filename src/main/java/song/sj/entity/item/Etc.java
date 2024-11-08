package song.sj.entity.item;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Etc extends Item{

    private String productClassification;

    public Etc(String productClassification) {
        this.productClassification = productClassification;
    }
}
