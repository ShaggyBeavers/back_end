package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "user_regions")
@Entity
@Getter
@Setter
public class UserRegion implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "user_id")
    private Long userId;

}
