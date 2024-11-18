package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import song.sj.entity.item.Item;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @JoinColumn(name = "orderShop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderShop orderShop;

    @JoinColumn(name = "item_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Item item;

    private int quantity;
}
