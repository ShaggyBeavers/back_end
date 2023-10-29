package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "author")
    private String author;

    @Column(name = "picture")
    private String picture;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "creation_place_id")
    private Region creationPlaceId;

    @OneToMany(mappedBy = "relic", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "relic", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<RelicCategory> relicCategories = new ArrayList<>();

    @OneToOne(mappedBy = "relic", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private RelicInfo relicInfo;

    @ManyToOne
    @JoinColumn(name = "museum_id")
    private Museum museum;

    @Column
    private Integer quantity;

    @Column
    private String collection;

    @Column
    private String comment;

    @Column
    private String copyInformation;

    @Column
    private LocalDate copyCreationTime;

    @Column
    private String entryBookNumber;

    @Column
    private Integer inventoryNumber;

    @Column
    private Integer formerInventoryNumber;

}
