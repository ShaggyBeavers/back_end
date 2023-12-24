package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.CategoryDTO;
import com.lpnu.shaggybeavers.factory.CategoryFactory;
import com.lpnu.shaggybeavers.model.Category;
import com.lpnu.shaggybeavers.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryFacade {

    private final CategoryFactory categoryFactory;

    private final CategoryService categoryService;

    public Category findById(Long id) {
        return categoryService.findById(id);
    }

    public List<CategoryDTO> readCategories() {
        return categoryFactory.toCategoryDTOList(categoryService.findAll());
    }

}
