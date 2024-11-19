package song.sj.dto.delivery;

import lombok.Data;
import song.sj.entity.Address;

@Data
public class DeliverySaveDto {

    private String username;
    private String email;
    private Address address;
}
