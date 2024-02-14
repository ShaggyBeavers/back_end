package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.ReportCategory;
import com.lpnu.shaggybeavers.repository.ReportCategoryRepository;
import com.lpnu.shaggybeavers.service.ReportCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportCategoryServiceImpl extends CRUDServiceImpl<ReportCategory, Long> implements ReportCategoryService {

    private final ReportCategoryRepository repository;

    @Override
    protected JpaRepository<ReportCategory, Long> getRepository () {
        return this.repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReportCategory> findAllByReportId(Long reportId) {
        return repository.findAllByReportId(reportId);
    }

}
