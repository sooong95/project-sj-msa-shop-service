package song.sj.dto.shop;

import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import song.sj.entity.Address;
import song.sj.enums.ItemValue;

import java.util.List;

@Data
@AllArgsConstructor
public class ShopSaveDto {

    private String shopName;
    private String ShopDescription;
    private List<ItemValue> mainEvent;

    private Address address;
}
