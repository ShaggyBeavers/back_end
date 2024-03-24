package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.CategoryCreateDTO;
import com.lpnu.shaggybeavers.dto.CategoryDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryFactory {

    private final EntityMapper entityMapper;

    public List<CategoryDTO> toCategoryDTOList(List<Category> categories) {
        return entityMapper.toCategoryDTOList(categories);
    }

    public Category toCategory(CategoryCreateDTO categoryCreateDTO) {
        return entityMapper.toCategory(categoryCreateDTO);
    }

    public CategoryDTO toCategoryDTO(Category category) {
        return entityMapper.toCategoryDTO(category);
    }

}
