package com.lpnu.shaggybeavers.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ModeratorCreateDTO {

    private Long userId;

    private Set<Long> regionIds;

    private Set<Long> categoryIds;

}
