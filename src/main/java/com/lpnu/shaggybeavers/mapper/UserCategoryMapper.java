package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.CategoryDTO;
import com.lpnu.shaggybeavers.model.UserCategory;
import org.mapstruct.Mapping;

public interface UserCategoryMapper {

    @Mapping(source = "category.id", target = "id")
    @Mapping(source = "category.categoryName", target = "categoryName")
    CategoryDTO toCategoryDTO(UserCategory userCategory);

}
