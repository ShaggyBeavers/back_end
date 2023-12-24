package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.filter.ReportByUserFilter;
import com.lpnu.shaggybeavers.model.Relic;
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
    protected JpaRepository<Report, Long> getRepository () {
        return this.repository;
    }
}
