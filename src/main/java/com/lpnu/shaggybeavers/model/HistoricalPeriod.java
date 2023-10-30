package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "historical_periods")
@Entity
@Getter
@Setter
public class HistoricalPeriod implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "historicalPeriod", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<RelicInfo> relicInfos = new ArrayList<>();
}
