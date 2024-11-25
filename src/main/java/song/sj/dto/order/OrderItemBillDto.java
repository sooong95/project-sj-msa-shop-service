package song.sj.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemBillDto {

    private int cost;
    private Long itemId;
    private String itemName;
    private int quantity;
    private String repairDescription;
}
