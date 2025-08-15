package song.sj.dto.feign_dto.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderItemInfoDto {

    private Long itemId;
    private String itemName;
    private String description;
    private List<String> itemImageUrlList;
    private int itemQuantity;
}
