package song.sj.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryDto {

    private LocalDateTime orderDate;
    private List<OrderHistoryShopDto> orderShopList;
}
