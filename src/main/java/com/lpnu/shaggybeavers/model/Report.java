package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Table(name = "reports")
@Entity
@Getter
@Setter
public class Report implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "relic_id")
    private Long relicId;

    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "submission_date")
    private LocalDate submissionDate;

    @Column(name = "map_location")
    private String mapLocation;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "comment")
    private String comment;


}
