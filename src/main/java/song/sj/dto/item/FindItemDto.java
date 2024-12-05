package song.sj.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindItemDto {

    private String itemName;
    private String material;
    private int size;
    private String design;
    private String description;
}
