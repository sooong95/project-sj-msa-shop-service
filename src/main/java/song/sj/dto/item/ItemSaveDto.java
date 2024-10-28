package song.sj.dto.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import song.sj.enums.ItemValue;

@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemSaveDto {

    private String itemName;
    private String material;
    private int size;
    private String design;
    private String description;
    private String productClassification;
    @Enumerated(EnumType.STRING)
    private ItemValue value;

    /*public ItemSaveDto(@JsonProperty String itemName, @JsonProperty String material, @JsonProperty int size, @JsonProperty String design, @JsonProperty String description, @JsonProperty String productClassification, @JsonProperty ItemValue value) {
        this.itemName = itemName;
        this.material = material;
        this.size = size;
        this.design = design;
        this.description = description;
        this.productClassification = productClassification;
        this.value = value;
    }*/
}
