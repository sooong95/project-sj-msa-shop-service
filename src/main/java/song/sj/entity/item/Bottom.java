package song.sj.entity.item;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Override
    public void changeItemName(String itemName) {
        super.changeItemName(itemName);
    }

    @Override
    public void changeMaterial(String material) {
        super.changeMaterial(material);
    }

    @Override
    public void changeSize(int size) {
        super.changeSize(size);
    }

    @Override
    public void changeDesign(String design) {
        super.changeDesign(design);
    }

    @Override
    public void changeDescription(String description) {
        super.changeDescription(description);
    }
}
