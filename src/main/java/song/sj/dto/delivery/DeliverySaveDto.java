package song.sj.dto.delivery;

import lombok.Data;
import song.sj.entity.Address;

@Data
public class DeliverySaveDto {

    private Long orderId;
    private String username;
    private String email;
    private String courierName;
    private String memo;
    private Address address;
}
