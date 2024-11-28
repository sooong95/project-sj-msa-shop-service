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
public class ItemCategory extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_category_id")
    private Long id;

    private String itemCategoryName;

    @JoinColumn(name = "parent_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ItemCategory parent;

    @OneToMany(mappedBy = "itemCategory")
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "parent")
    private List<ItemCategory> subCategory = new ArrayList<>();

    @OneToMany(mappedBy = "itemCategory")
    private List<ShopItemCategoryMiddleTable> shopItemCategoryMiddleTableList = new ArrayList<>();

    public ItemCategory(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }
}
