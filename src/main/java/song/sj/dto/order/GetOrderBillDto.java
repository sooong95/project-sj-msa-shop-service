package song.sj.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderBillDto {

    private String shopName;
    private String shopEmail;
    private String clientEmail;
    private LocalDateTime billDate;
    private List<GetOrderItemBillDto> itemBillDtoList;
    private int totalCost;
}
