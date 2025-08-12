package song.sj.service.external_service.order;

import song.sj.dto.Result;
import song.sj.dto.shop.ShopInfoDto;

import java.util.List;

public interface ExternalOrderService {

    List<ShopInfoDto> getShopInfo(List<Long> shopIdList);
}
