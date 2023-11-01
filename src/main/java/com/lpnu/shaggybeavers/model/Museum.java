package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "museums")
@Entity
@Getter
@Setter
public class Museum implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_old")
    private String nameOld;

    @Column(name = "is_destroyed")
    private Boolean isDestroyed;

    @OneToMany(mappedBy = "museum", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<LostRelicInfo> lostRelicInfos = new ArrayList<>();

    @OneToMany(mappedBy = "museum", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Relic> relics = new ArrayList<>();

}
