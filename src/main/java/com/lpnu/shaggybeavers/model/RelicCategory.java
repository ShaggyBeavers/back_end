package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "relic_categories")
@Entity
@Getter
@Setter
public class RelicCategory implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "relic_id")
    private Relic relic;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
