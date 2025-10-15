package song.sj.service.query.redis;

import org.springframework.data.domain.Pageable;
import song.sj.dto.shop.ShopSearchConditionDto;

import java.util.Objects;

public class CacheKeyUtil {

    public static String generateKey(ShopSearchConditionDto dto, Pageable pageable) {
        StringBuilder keyBuilder = new StringBuilder();

        if (dto.getItemCategoryName() != null) {
            keyBuilder.append("itemCategoryName=").append(dto.getItemCategoryName() != null ? dto.getItemCategoryName() : "ALL").append("|");
        }

        keyBuilder.append("grade=").append(dto.getGrade()).append("|");
        keyBuilder.append("reviewsCount=").append(dto.getReviewsCount()).append("|");
        keyBuilder.append("wishlistCount=").append(dto.getWishlistCount()).append("|");
        keyBuilder.append("sortField=").append(Objects.toString(dto.getSortField(), "DEFAULT")).append("|");
        keyBuilder.append("sortOrder=").append(Objects.toString(dto.getSortOrder(), "ASC")).append("|");

        if (pageable != null) {
            keyBuilder.append("page=").append(pageable.getPageNumber()).append("|");
            keyBuilder.append("size=").append(pageable.getPageSize());
        }

        return keyBuilder.toString();
    }
}
