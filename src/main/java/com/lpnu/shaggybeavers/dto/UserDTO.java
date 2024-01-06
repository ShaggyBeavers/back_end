package com.lpnu.shaggybeavers.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDTO {

    private String firstName;

    private String lastName;

    private List<CategoryDTO> categories;

    private List<RegionDTO> regions;

}
