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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "relic_id")
    private Long relicId;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

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
