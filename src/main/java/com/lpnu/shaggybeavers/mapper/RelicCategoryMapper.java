package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.RelicCategoryCreateDTO;
import com.lpnu.shaggybeavers.model.Category;
import com.lpnu.shaggybeavers.model.Relic;
import com.lpnu.shaggybeavers.model.RelicCategory;

public interface RelicCategoryMapper {

    RelicCategory toRelicCategory(RelicCategoryCreateDTO relicCategoryCreateDTO);
}
