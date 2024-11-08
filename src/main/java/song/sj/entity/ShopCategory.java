package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import song.sj.TimeStamp;
import song.sj.entity.item.Item;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopCategory extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_category_id")
    private Long id;

    private String shopCategoryName;

    @JoinColumn(name = "parent_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ShopCategory parent;

    @OneToMany(mappedBy = "parent")
    private List<ShopCategory> subCategory = new ArrayList<>();

    @OneToMany(mappedBy = "shopCategory")
    private List<ShopCategoryMiddleTable> shopCategoryMiddleTableList = new ArrayList<>();

    public ShopCategory(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName;
    }
}
