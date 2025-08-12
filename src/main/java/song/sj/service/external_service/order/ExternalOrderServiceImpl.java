package song.sj.service.external_service.order;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.Result;
import song.sj.dto.shop.ShopInfoDto;
import song.sj.entity.Shop;
import song.sj.repository.ShopRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExternalOrderServiceImpl implements ExternalOrderService {

    private final ShopRepository shopRepository;

    @Override
    public List<ShopInfoDto> getShopInfo(List<Long> shopIdList) {

        List<ShopInfoDto> shopInfoDtoList = new ArrayList<>();

        for (Long shopId : shopIdList) {
            Shop shop = shopRepository.findById(shopId).orElseThrow(() ->
                    new EntityNotFoundException("존재하지 않는 shop 입니다."));

            shopInfoDtoList.add(ShopInfoDto.builder()
                    .shopId(shop.getId())
                    .shopName(shop.getShopName())
                    .shopDescription(shop.getShopDescription())
                    .city(shop.getAddress().getCity())
                    .street(shop.getAddress().getStreet())
                    .zipcode(shop.getAddress().getZipcode())
                    .build());
        }

        return shopInfoDtoList;
    }
}
