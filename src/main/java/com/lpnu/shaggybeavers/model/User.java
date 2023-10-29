package com.lpnu.shaggybeavers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
public class User implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;


    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserRegion> userRegions = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserCategory> userCategories = new ArrayList<>();

}
