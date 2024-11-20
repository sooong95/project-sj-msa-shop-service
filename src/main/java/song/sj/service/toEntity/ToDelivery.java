package song.sj.service.toEntity;

import song.sj.dto.delivery.DeliverySaveDto;
import song.sj.dto.item.ItemSaveDto;
import song.sj.entity.Delivery;
import song.sj.entity.item.*;

public class ToDelivery {

    public static Delivery toItemEntity(DeliverySaveDto dto) {

        return new Delivery(dto.getCourierName(), dto.getMemo(), dto.getAddress());
    }
}
