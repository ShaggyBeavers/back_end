package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.ReportCategory;
import com.lpnu.shaggybeavers.repository.ReportCategoryRepository;
import com.lpnu.shaggybeavers.service.ReportCategoryService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReportCategoryServiceImpl extends CRUDServiceImpl<ReportCategory, Long> implements ReportCategoryService {

    private final ReportCategoryRepository repository;

    @Lazy
    public ReportCategoryServiceImpl(ReportCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<ReportCategory, Long> getRepository () {
        return this.repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReportCategory> findAllByReportId(Long reportId) {
        return repository.findAllByReportId(reportId);
    }

    @Override
    public boolean existsByCategoryId(Long categoryId) {
        return repository.existsByCategoryId(categoryId);
    }

}
