package song.sj.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderItemBillDto {

    private int cost;
    private String itemName;
    private int quantity;
    private String repairDescription;
}
