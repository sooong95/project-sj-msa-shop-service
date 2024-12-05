package song.sj.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchItemDto {

    private String itemName;
    private String categoryName;
    private int size;
    private List<String> imageURL;
}
