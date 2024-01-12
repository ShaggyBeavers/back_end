package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.CategoryCreateDTO;
import com.lpnu.shaggybeavers.dto.CategoryDTO;
import com.lpnu.shaggybeavers.model.Category;

import java.util.List;

public interface CategoryMapper {

    List<CategoryDTO> toCategoryDTOList(List<Category> categories);

    Category toCategory(CategoryCreateDTO categoryCreateDTO);

}
