package song.sj.dto.feign_dto;

import lombok.Data;

import java.util.List;

@Data
public class ItemCategoryDto {

    private Long itemCategoryId;
    private List<String> itemCategoryName;
}
