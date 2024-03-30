package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "relic_files")
@Entity
@Getter
@Setter
public class RelicFile extends FileEntity<String> implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "relic_id")
    private Relic relic;

    @Column(name = "extension")
    private String extension;

}
