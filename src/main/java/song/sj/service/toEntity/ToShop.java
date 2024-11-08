package song.sj.service.toEntity;

import lombok.Getter;
import song.sj.dto.shop.ShopSaveDto;
import song.sj.entity.Shop;

@Getter
public class ToShop {

    /*private static Shop initializeField(Shop shop, ShopSaveDto dto) {


        return shop;
    }*/

    public static Shop toShopEntity(Shop shop, ShopSaveDto dto) {

        shop.changeShopName(dto.getShopName());
        shop.changeShopDescription(dto.getShopDescription());
        shop.changeMainEvent(dto.getMainEvent());
        shop.changeAddress(dto.getAddress());

        return shop;
    }
}
