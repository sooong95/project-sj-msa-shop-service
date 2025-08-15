package song.sj.dto.feign_dto.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderInfoDto {

    private int count;
    private Long orderId;
    private Long shopId;
    private List<OrderItemInfoDto> orderItemDtoList;
}
