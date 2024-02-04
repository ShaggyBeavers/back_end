package com.lpnu.shaggybeavers.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ChangeModeratorCategoriesDTO {

    private Long moderatorId;

    private Set<Long> categoryIds;

}
