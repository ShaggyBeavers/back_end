package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "relic_info")
@Entity
@Getter
@Setter
public class RelicInfo implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "relic_id")
    private Relic relic;

    @ManyToOne
    @JoinColumn(name = "technique_id")
    private Technique technique;

    @ManyToOne
    @JoinColumn(name = "historical_period_id")
    private HistoricalPeriod historicalPeriod;

    @Column
    private String dimensions;

    @Column
    private String marks;

    @Column
    private String labels;

    @Column
    private String signatures;

    @Column
    private String restoration;

    @Column
    private String appraised_value;

    @Column
    private String insurance_value;

    @Column
    private String annotation;

    @OneToOne(mappedBy = "relicInfo", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private LostRelicInfo lostRelicInfo;

    @OneToOne(mappedBy = "relicInfo", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private RecoveredRelicInfo recoveredRelicInfo;
}
