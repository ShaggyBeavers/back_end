package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.CategoryProperty;
import com.lpnu.shaggybeavers.repository.CategoryPropertyRepository;
import com.lpnu.shaggybeavers.service.CategoryPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryPropertyServiceImpl extends CRUDServiceImpl<CategoryProperty, Long> implements CategoryPropertyService {

    private final CategoryPropertyRepository categoryPropertyRepository;

    @Override
    protected JpaRepository<CategoryProperty, Long> getRepository() {
        return this.categoryPropertyRepository;
    }
}
