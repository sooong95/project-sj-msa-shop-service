package song.sj.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryShopDto {

    private String shopName;
    private int itemCount;
    private List<OrderHistoryItemDto> itemList;
}
