package song.sj.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FindItemDto {

    private String itemName;
    private String material;
    private int size;
    private String design;
    private String description;

    private List<byte[]> image;
}
