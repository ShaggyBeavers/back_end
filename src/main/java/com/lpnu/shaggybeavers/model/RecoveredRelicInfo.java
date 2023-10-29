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

    @Column
    private String location_source;

    @Column
    private LocalDate previous_search_info;

    @Column
    private String return_process;

    @Column
    private String court_decision;

    @Column
    private LocalDate return_date;

}


