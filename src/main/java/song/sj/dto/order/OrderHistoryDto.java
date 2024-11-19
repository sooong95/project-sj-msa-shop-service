package song.sj.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderHistoryDto {

    private List<OrderShopDto> orderShopDtoList;
}
