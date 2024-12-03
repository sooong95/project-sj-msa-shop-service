package song.sj.dto.shop;

import lombok.Data;
import song.sj.entity.Wishlist;

import java.util.List;

@Data
public class ShopSearchConditionDto {

    private List<String> shopItemCategoryName;
    private double grade;
    private int reviewsCount;
    private int wishlistCount;

    private String sortField;
    private String sortOrder;
}
