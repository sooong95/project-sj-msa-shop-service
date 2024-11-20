package song.sj.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import song.sj.enums.OrderStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryItemDto {

    private String itemName;
    private int quantity;
    private OrderStatus orderStatus;
    private List<String> itemImageUrlList;
}
