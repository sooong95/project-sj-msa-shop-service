package song.sj.dto.delivery;

import lombok.Data;
import song.sj.enums.DeliveryStatus;

@Data
public class ChangeDeliveryStatusDto {

    private Long deliveryId;
    private DeliveryStatus deliveryStatus;
}
