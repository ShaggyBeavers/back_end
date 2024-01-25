package com.lpnu.shaggybeavers.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RegionalModeratorDTO {

    private Long userId;

    private Set<Long> regionIds;

}
