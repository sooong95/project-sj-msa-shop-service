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
public class Category extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String categoryName;

    @JoinColumn(name = "parent_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    @OneToMany(mappedBy = "category")
    private List<Item> items = new ArrayList<>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
