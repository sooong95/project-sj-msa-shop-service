package song.sj.entity;

import jakarta.persistence.*;
import song.sj.TimeStamp;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Shop extends TimeStamp {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private Long id;
}
