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

    @OneToMany(mappedBy = "region")
    private List<UserRegion> userRegions = new ArrayList<>();

    @OneToMany(mappedBy = "region")
    private List<Report> report = new ArrayList<>();


}
