package song.sj.service.query;

import org.springframework.data.domain.Pageable;
import song.sj.dto.PageResponseDto;
import song.sj.dto.shop.ShopConditionSearchListDto;
import song.sj.dto.shop.ShopSearchConditionDto;

public interface ShopQueryService {

    PageResponseDto<ShopConditionSearchListDto> ShopConditionSearchList(ShopSearchConditionDto dto, Pageable pageable);
}
