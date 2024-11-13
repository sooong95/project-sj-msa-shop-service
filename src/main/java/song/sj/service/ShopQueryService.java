package song.sj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.Result;
import song.sj.dto.shop.ShopListAllDto;
import song.sj.repository.ShopRepository;
import song.sj.repository.query.ShopQueryRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShopQueryService {

    private final ShopRepository shopRepository;

    /*public Result<List<ShopListAllDto>> shopListAll() {
        shopQueryRepository.listAll()
                .stream().map(list -> new ShopListAllDto(list.getShopName(), list.getMainEvent(), list.getTotalReviewsCount(), list.getTotalWishlistCount(), list.));

    }*/
}
