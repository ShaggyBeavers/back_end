package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "relics")
@Entity
@Getter
@Setter
public class RelicExample implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String object_type;
    private String status;
    private LocalDate theft_date;
    private LocalDate creation_date;
    private String author;
    private String picture;
    private String description;
    private String probable_location;
    private Long region_id;
    private String map_location;
    private String court_decisions;
    private String name;
    private Long historic_region_id;
}
