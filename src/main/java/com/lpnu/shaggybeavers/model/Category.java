package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "categories")
@Entity
@Getter
@Setter
public class Category implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<UserCategory> userCategories = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<RelicCategory> relicCategories = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<ReportCategory> reportCategories = new ArrayList<>();

}
