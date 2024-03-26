package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.CategoryCreateDTO;
import com.lpnu.shaggybeavers.dto.CategoryDTO;
import com.lpnu.shaggybeavers.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

public interface CategoryMapper {

    List<CategoryDTO> toCategoryDTOList(List<Category> categories);

    @Mapping(source = "name", target = "categoryName")
    Category toCategory(CategoryCreateDTO categoryCreateDTO);

    @Mapping(source = "categoryName", target = "name")
    CategoryDTO toCategoryDTO(Category category);

}
