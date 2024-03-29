package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.RelicCategory;
import com.lpnu.shaggybeavers.repository.RelicCategoryRepository;
import com.lpnu.shaggybeavers.service.CategoryService;
import com.lpnu.shaggybeavers.service.RelicCategoryService;
import com.lpnu.shaggybeavers.service.RelicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RelicCategoryServiceImpl extends CRUDServiceImpl<RelicCategory, Long> implements RelicCategoryService {

    private final RelicCategoryRepository relicCategoryRepository;

    private final CategoryService categoryService;

    private final RelicService relicService;

    @Override
    public JpaRepository<RelicCategory, Long> getRepository() {
        return this.relicCategoryRepository;
    }

    @Override
    @Transactional
    public void create(Long relicId, Long categoryId) {
        RelicCategory relicCategory = new RelicCategory();
        relicCategory.setCategory(categoryService.findById(categoryId));
        relicCategory.setRelic(relicService.findById(relicId));
        relicCategoryRepository.save(relicCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RelicCategory> findAllByRelicId(Long relicId) {
        return relicCategoryRepository.findAllByRelicId(relicId);
    }

}
