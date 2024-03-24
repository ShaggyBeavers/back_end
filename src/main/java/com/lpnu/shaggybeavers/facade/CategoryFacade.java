package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.CategoryCreateDTO;
import com.lpnu.shaggybeavers.dto.CategoryDTO;
import com.lpnu.shaggybeavers.factory.CategoryFactory;
import com.lpnu.shaggybeavers.model.Category;
import com.lpnu.shaggybeavers.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryFacade {

    private final CategoryFactory categoryFactory;

    private final CategoryService categoryService;

    @Transactional
    public Category findById(Long id) {
        return categoryService.findById(id);
    }

    @Transactional
    public List<CategoryDTO> readCategories() {
        return categoryFactory.toCategoryDTOList(categoryService.findAll());
    }

    @Transactional
    public void createCategory(CategoryCreateDTO categoryCreateDTO) {
        categoryService.save(categoryFactory.toCategory(categoryCreateDTO));
    }

    @Transactional
    public CategoryDTO getById(Long categoryId) {
        return categoryFactory.toCategoryDTO(categoryService.findById(categoryId));
    }

    @Transactional
    public void deleteById(Long categoryId) {
        categoryService.deleteById(categoryId);
    }

}
