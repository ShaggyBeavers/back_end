package com.lpnu.shaggybeavers.dto;

import com.lpnu.shaggybeavers.domain.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ModeratorDTO {

    private Long userId;

    private Set<Long> regionIds;

    private Set<Long> categoryIds;

    private RoleEnum role;

}
