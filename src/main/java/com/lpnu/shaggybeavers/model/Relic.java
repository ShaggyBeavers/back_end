package com.lpnu.shaggybeavers.model;

import com.lpnu.shaggybeavers.domain.RelicStatus;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RelicStatus status;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "author")
    private String author;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "creation_place_id")
    private Region creationPlace;

    @OneToMany(mappedBy = "relic", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "relic", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<RelicCategory> relicCategories = new ArrayList<>();

    @OneToOne(mappedBy = "relic", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private RelicInfo relicInfo;

    @ManyToOne
    @JoinColumn(name = "museum_id")
    private Museum museum;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "collection")
    private String collection;

    @Column(name = "comment")
    private String comment;

    @Column(name = "copy_information")
    private String copyInformation;

    @Column(name = "copy_creation_time")
    private LocalDate copyCreationTime;

    @Column(name = "entry_book_number")
    private String entryBookNumber;

    @Column(name = "inventory_number")
    private Integer inventoryNumber;

    @Column(name = "former_inventory_number")
    private Integer formerInventoryNumber;

    @OneToMany(mappedBy = "relic", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<RelicProperty> relicProperties = new ArrayList<>();

    @Column(name = "image_url")
    private String imageUrl;

}
