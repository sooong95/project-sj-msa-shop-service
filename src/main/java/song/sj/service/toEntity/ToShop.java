package song.sj.service.toEntity;

import lombok.Getter;
import song.sj.dto.shop.ShopSaveDto;
import song.sj.entity.Shop;

@Getter
public class ToShop {

    /*private static Shop initializeField(Shop shop, ShopSaveDto dto) {


        return shop;
    }*/

    public static Shop toShopEntity(ShopSaveDto dto) {

        return new Shop(dto.getShopName(), dto.getShopDescription(), dto.getMainEvent(), dto.getAddress());
    }
}
