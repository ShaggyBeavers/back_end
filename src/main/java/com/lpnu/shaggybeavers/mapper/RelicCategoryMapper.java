package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.CategoryDTO;
import com.lpnu.shaggybeavers.model.RelicCategory;
import org.mapstruct.Mapping;

public interface RelicCategoryMapper {

    @Mapping(source = "category.id", target = "id")
    @Mapping(source = "category.categoryName", target = "categoryName")
    CategoryDTO toCategoryDTO(RelicCategory relicCategory);

}
