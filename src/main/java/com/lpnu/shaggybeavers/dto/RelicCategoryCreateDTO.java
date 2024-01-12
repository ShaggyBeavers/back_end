package com.lpnu.shaggybeavers.dto;

import com.lpnu.shaggybeavers.model.Category;
import com.lpnu.shaggybeavers.model.Relic;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelicCategoryCreateDTO {

    private Category category;

    private Relic relic;
}
