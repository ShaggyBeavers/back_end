package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "regions")
@Entity
@Getter
@Setter
public class Region implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "region",cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<UserRegion> userRegions = new ArrayList<>();

    @OneToMany(mappedBy = "region",cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Report> report = new ArrayList<>();

    @OneToMany(mappedBy = "region", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Relic> relicsByRegion = new ArrayList<>();

    @OneToMany(mappedBy = "creationPlaceId", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Relic> relicsByCreationPlace = new ArrayList<>();
}
