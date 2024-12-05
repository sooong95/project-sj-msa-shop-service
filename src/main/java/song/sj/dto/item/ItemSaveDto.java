package song.sj.dto.item;

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
    private int quantity;
    private String design;
    private String description;
    private String productClassification;
    @Enumerated(EnumType.STRING)
    private ItemValue value;

}
