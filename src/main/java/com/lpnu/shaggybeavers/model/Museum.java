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

    @Column
    private String name;

    @Column
    private String name_old;

    @Column
    private Boolean is_destroyed;

    @OneToMany(mappedBy = "museum", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<LostRelicInfo> lostRelicInfos = new ArrayList<>();

    @OneToMany(mappedBy = "museum", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Relic> relics = new ArrayList<>();

}
