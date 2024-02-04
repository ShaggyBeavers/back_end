package com.lpnu.shaggybeavers.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ChangeModeratorRegionsDTO {

    private Long moderatorId;

    private Set<Long> regionIds;

}
