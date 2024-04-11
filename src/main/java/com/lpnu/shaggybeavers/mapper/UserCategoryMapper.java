package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.CategoryDTO;
import com.lpnu.shaggybeavers.model.ReportCategory;
import com.lpnu.shaggybeavers.model.UserCategory;
import org.mapstruct.Mapping;

public interface UserCategoryMapper {
    @Mapping(source = "category.categoryName", target = "name")
    @Mapping(source = "category.id", target = "id")
    CategoryDTO toCategoryDTO(UserCategory userCategory);

}
