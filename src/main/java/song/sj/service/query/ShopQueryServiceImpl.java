package song.sj.service.query;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.PageResponseDto;
import song.sj.dto.shop.ShopConditionSearchListDto;
import song.sj.dto.shop.ShopSearchConditionDto;
import song.sj.repository.ShopRepository;
import song.sj.service.image.ImageFile;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShopQueryServiceImpl implements ShopQueryService{

    private final ShopRepository shopRepository;
    private final ImageFile imageFile;

    @Override
    @Cacheable(cacheNames = "getShopList",
    key = "'getShopList:condition:' + T(song.sj.service.query.redis.CacheKeyUtil).generateKey(#dto, #pageable)")
    public PageResponseDto<ShopConditionSearchListDto> ShopConditionSearchList(ShopSearchConditionDto dto, Pageable pageable) {

        Page<ShopConditionSearchListDto> page = shopRepository.ShopConditionSearchList(dto, pageable)
                .map(shop -> new ShopConditionSearchListDto(shop.getShopName(),
                                shop.getShopImages().stream().map(image -> imageFile.getFullPath(image.getImageName())).toList(),
                                shop.getTotalReviewCount(),
                                shop.getTotalWishlistCount(),
                                shop.getAverageGrade(),
                                null
                                /*shop.getReviewList().stream().map(Review::getReviewTitle).limit(5).toList()*/
                        )
                );
        return PageResponseDto.<ShopConditionSearchListDto>builder()
                .content(page.getContent())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }
}
