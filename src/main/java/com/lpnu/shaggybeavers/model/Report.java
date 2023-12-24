package com.lpnu.shaggybeavers.model;

import com.lpnu.shaggybeavers.domain.ReportStatus;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReportStatus status;

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
