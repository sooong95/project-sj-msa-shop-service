package song.sj.dto.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import song.sj.entity.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopListAllDto {

    private String shopName;
    private List<String> mainEvent;

    private int totalReviewsCount;
    private int totalWishlistCount;
    private double grade;

    private Address address;
    private ShopImages shopImages;
    private List<String> reviewList;
}
