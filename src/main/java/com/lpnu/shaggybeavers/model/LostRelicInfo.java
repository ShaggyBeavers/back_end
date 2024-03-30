package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "loss_way")
    private String lossWay;

    @Column(name = "loss_time")
    private String lossTime;

    @Column(name = "probable_location")
    private String probableLocation;

}
