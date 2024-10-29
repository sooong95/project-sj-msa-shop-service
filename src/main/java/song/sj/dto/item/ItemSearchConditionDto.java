package song.sj.dto.item;

import lombok.Data;
import song.sj.enums.ItemValue;

@Data
public class ItemSearchConditionDto {

    private String itemName;
    private String categoryName;
}
