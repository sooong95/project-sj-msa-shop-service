package song.sj.dto.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import song.sj.entity.*;
import song.sj.enums.ItemValue;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopListAllDto {

    private String shopName;
    private List<ItemValue> mainEvent;

    private int totalReviewsCount;
    private int totalWishlistCount;
    private double grade;

    private Address address;
    private ShopImages shopImages;
    private List<Review> reviewList;
}
