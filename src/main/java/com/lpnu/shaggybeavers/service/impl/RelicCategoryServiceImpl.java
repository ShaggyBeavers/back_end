package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.RelicCategory;
import com.lpnu.shaggybeavers.repository.RelicCategoryRepository;
import com.lpnu.shaggybeavers.service.RelicCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelicCategoryServiceImpl extends CRUDServiceImpl<RelicCategory, Long> implements RelicCategoryService {

    private final RelicCategoryRepository relicCategoryRepository;

    @Override
    public JpaRepository<RelicCategory, Long> getRepository() {
        return this.relicCategoryRepository;
    }
}
