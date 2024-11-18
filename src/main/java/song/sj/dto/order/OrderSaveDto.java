package song.sj.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderSaveDto {

    private List<Long> itemList;
    private List<Long> shopList;
}
