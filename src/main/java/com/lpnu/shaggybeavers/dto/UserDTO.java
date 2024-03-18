package com.lpnu.shaggybeavers.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDTO {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private List<CategoryDTO> categories;

    private List<RegionDTO> regions;

    private boolean ban;

}
