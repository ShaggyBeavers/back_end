package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.exception.AssociationException;
import com.lpnu.shaggybeavers.exception.DuplicateException;
import com.lpnu.shaggybeavers.model.Category;
import com.lpnu.shaggybeavers.repository.CategoryRepository;
import com.lpnu.shaggybeavers.service.CategoryService;
import com.lpnu.shaggybeavers.service.RelicCategoryService;
import com.lpnu.shaggybeavers.service.ReportCategoryService;
import com.lpnu.shaggybeavers.service.UserCategoryService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends CRUDServiceImpl<Category, Long> implements CategoryService {

    private final RelicCategoryService relicCategoryService;

    private final ReportCategoryService reportCategoryService;

    private final UserCategoryService userCategoryService;

    private final CategoryRepository categoryRepository;

    @Lazy
    public CategoryServiceImpl(RelicCategoryService relicCategoryService, ReportCategoryService reportCategoryService, UserCategoryService userCategoryService, CategoryRepository categoryRepository) {
        this.relicCategoryService = relicCategoryService;
        this.reportCategoryService = reportCategoryService;
        this.userCategoryService = userCategoryService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected JpaRepository<Category, Long> getRepository() {
        return this.categoryRepository;
    }

    @Override
    @Transactional
    public Category save(Category category) {
        if (categoryRepository.findByCategoryName(category.getCategoryName()) != null)  {
            throw new DuplicateException("Category already exists");
        }
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category deleteById(Long categoryId) {
        Category category = findById(categoryId);

        if (relicCategoryService.existsByCategoryId(categoryId)) {
            throw new AssociationException("The category is associated with a relic");
        }
        if (reportCategoryService.existsByCategoryId(categoryId)) {
            throw new AssociationException("The category is associated with a report");
        }
        if (userCategoryService.existsByCategoryId(categoryId)) {
            throw new AssociationException("The category is associated with a user");
        }

        categoryRepository.delete(category);
        return category;
    }

}
