package song.sj.entity;

import jakarta.persistence.*;
import song.sj.TimeStamp;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Item extends TimeStamp {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "item_id")
    private Long id;
}
