package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.exception.NotExistsObjectException;
import com.lpnu.shaggybeavers.model.Report;
import com.lpnu.shaggybeavers.repository.ReportRepository;
import com.lpnu.shaggybeavers.service.ReportService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl extends CRUDServiceImpl<Report,Long> implements ReportService {

    private final ReportRepository repository;

    @Override
    @Transactional
    public Page<Report> findAll(Pageable pageable, Specification<Report> specification) {
        return repository.findAll(specification, pageable);
    }

    @Override
    public Page<Report> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    protected JpaRepository<Report, Long> getRepository () {
        return this.repository;
    }

    @Override
    public Report findByIdAbsolute(Long reportId) {
        return repository.findByIdAbsolute(reportId).orElseThrow(NotExistsObjectException::new);
    }

    @Override
    public Page<Report> getModeratorReports(Set<Long> userRegions, Set<Long> userCategories, Long userId, Pageable pageable) {
        return repository.getModeratorReports(userRegions, userCategories, userId, pageable);
    }

    @Override
    public Page<Report> getRegionalModeratorReports(Set<Long> userRegions, Long userId, Pageable pageable) {
        return repository.getRegionalModeratorReports(userRegions, userId, pageable);
    }

    @Override
    public Page<Report> findAllByUserId(Long userId, Pageable pageable) {
        return repository.findAllByUserId(userId, pageable);
    }

}
