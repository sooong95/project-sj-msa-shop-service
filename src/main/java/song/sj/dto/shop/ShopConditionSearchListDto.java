package song.sj.dto.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopConditionSearchListDto {

    private String shopName;
    private List<String> imageUrl;
    private int reviewsCount;
    private int wishlistCount;
    private double grade;
    private List<String> mainEvent;
    private List<String> reviewTitleList;
}
