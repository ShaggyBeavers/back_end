package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "relics")
@Entity
@Getter
@Setter
public class Relic implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "object_type")
    private String objectType;

    @Column(name = "status")
    private String status;

    @Column(name = "theft_date")
    private LocalDate theftDate;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "author")
    private String author;

    @Column(name = "picture")
    private String picture;

    @Column(name = "description")
    private String description;

    @Column(name = "probable_location")
    private String probableLocation;

    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "map_location")
    private String mapLocation;

    @Column(name = "court_decisions")
    private String courtDecisions;

    @Column(name = "name")
    private String name;

    @Column(name = "historic_region_id")
    private Long historicRegionId;
}
