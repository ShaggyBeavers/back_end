package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "relic_id")
    private Relic relic;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name = "submission_date")
    private LocalDate submissionDate;

    @Column(name = "map_location")
    private String mapLocation;

    @Column(name = "description")
    private String description;

    @Column(name = "report_status")
    private String status;

    @Column(name = "comment")
    private String comment;

    @Column(name = "info_references")
    private String infoReferences;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "report", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<ReportCategory> reportCategories = new ArrayList<>();

}
