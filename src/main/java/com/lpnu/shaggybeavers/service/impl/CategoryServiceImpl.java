package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.exception.DuplicateException;
import com.lpnu.shaggybeavers.model.Category;
import com.lpnu.shaggybeavers.repository.CategoryRepository;
import com.lpnu.shaggybeavers.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends CRUDServiceImpl<Category, Long> implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    protected JpaRepository<Category, Long> getRepository() {
        return this.categoryRepository;
    }

    @Override
    public Category save(Category category) {
        if (categoryRepository.findByCategoryName(category.getCategoryName()) != null)  {
            throw new DuplicateException("Category already exists");
        }
        return categoryRepository.save(category);
    }
}
