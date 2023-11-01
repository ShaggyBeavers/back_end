package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "properties")
@Entity
@Getter
@Setter
public class Property implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "property")
    private String property;

    @OneToMany(mappedBy = "property", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<CategoryProperty> categoryProperties = new ArrayList<>();

}
