package song.sj.service.toEntity;

import song.sj.dto.order.OrderBillDto;
import song.sj.entity.Bill;

public class ToBill {

    public static Bill toBillEntity(OrderBillDto dto) {

        return new Bill(dto.getTotalCost());
    }
}
