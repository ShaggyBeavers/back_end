package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "lost_relic_info")
@Entity
@Getter
@Setter
public class LostRelicInfo implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "relic_info_id")
    private RelicInfo relicInfo;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Museum museum;

    @Column
    private String loss_way;

    @Column
    private LocalDate loss_time;

    @Column
    private String probableLocation;

}
