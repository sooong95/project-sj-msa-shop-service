package song.sj.service.toEntity;

import song.sj.dto.delivery.deliveryManagementSaveDto;
import song.sj.entity.Delivery;

public class ToDelivery {

    public static Delivery toItemEntity(deliveryManagementSaveDto dto) {

        return new Delivery(dto.getCourierName(), dto.getTransportationNumber(), dto.getMemo(), dto.getAddress());
    }
}
