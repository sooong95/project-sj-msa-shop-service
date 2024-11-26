package song.sj.service.toEntity;

import song.sj.dto.delivery.DeliveryManagementSaveDto;
import song.sj.entity.Delivery;

public class ToDelivery {

    public static Delivery toItemEntity(DeliveryManagementSaveDto dto) {

        return new Delivery(dto.getCourierName(), dto.getTransportationNumber(), dto.getMemo(), dto.getAddress());
    }
}
