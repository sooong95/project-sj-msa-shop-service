package song.sj.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderShopDto {

    private Long shopId;
    private List<OrderItemDto> orderItemList;
}
