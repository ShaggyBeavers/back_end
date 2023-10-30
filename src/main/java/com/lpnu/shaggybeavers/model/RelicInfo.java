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

    @Column(name = "dimensions")
    private String dimensions;

    @Column(name = "marks")
    private String marks;

    @Column(name = "labels")
    private String labels;

    @Column(name = "signatures")
    private String signatures;

    @Column(name = "restoration")
    private String restoration;

    @Column(name = "appraised_value")
    private String appraisedValue;

    @Column(name = "insurance_value")
    private String insuranceValue;

    @Column(name = "annotation")
    private String annotation;

    @OneToOne(mappedBy = "relicInfo", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private LostRelicInfo lostRelicInfo;

    @OneToOne(mappedBy = "relicInfo", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private RecoveredRelicInfo recoveredRelicInfo;
}
