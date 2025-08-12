package song.sj.dto.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShopInfoDto {

    private Long shopId;
    private String shopName;
    private String shopDescription;

    private String city;
    private String street;
    private String zipcode;
}
