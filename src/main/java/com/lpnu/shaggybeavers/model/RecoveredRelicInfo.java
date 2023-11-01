package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "recovered_relic_info")
@Entity
@Getter
@Setter
public class RecoveredRelicInfo implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "relic_info_id")
    private RelicInfo relicInfo;

    @Column(name = "location_source")
    private String locationSource;

    @Column(name = "previous_search_info")
    private LocalDate previousSearchInfo;

    @Column(name = "return_process")
    private String returnProcess;

    @Column(name = "court_decision")
    private String courtDecision;

    @Column(name = "return_date")
    private LocalDate returnDate;

}


