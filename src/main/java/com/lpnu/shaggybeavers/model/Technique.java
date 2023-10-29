package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "techniques")
@Entity
@Getter
@Setter
public class Technique implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "technique", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<RelicInfo> relicInfos = new ArrayList<>();
}
