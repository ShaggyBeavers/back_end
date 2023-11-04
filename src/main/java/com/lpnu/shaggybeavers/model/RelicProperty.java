package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "relic_properties")
@Entity
@Getter
@Setter
public class RelicProperty implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "relic_id")
    private Relic relic;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

}
