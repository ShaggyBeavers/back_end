package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "report_categories")
@Entity
@Getter
@Setter
public class ReportCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
