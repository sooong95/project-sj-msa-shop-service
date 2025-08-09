package song.sj.dto.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import song.sj.entity.Address;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopSaveDto {

    private String shopName;
    private String shopDescription;
    private List<String> mainEvent;

    private Address address;
}
