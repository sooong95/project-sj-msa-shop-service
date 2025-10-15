package song.sj.dto.shop;

import lombok.Data;

import java.util.List;

@Data
public class ShopSearchConditionDto {

    private List<String> itemCategoryName;
    private double grade;
    private int reviewsCount;
    private int wishlistCount;

    private String sortField;
    private String sortOrder;

    @Override
    public String toString() {
        return "ShopSearchConditionDto{" +
                "itemCategoryName=" + itemCategoryName +
                ", grade=" + grade +
                ", reviewsCount=" + reviewsCount +
                ", wishlistCount=" + wishlistCount +
                ", sortField='" + sortField + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                '}';
    }
}
