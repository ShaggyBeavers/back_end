package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public interface ReportService extends CRUDService<Report,Long> {

    Page<Report> findAll(Pageable pageable, Specification<Report> specification);

    Page<Report> findAll(Pageable pageable);

    Report findByIdAbsolute(Long reportId);

    Page<Report> getModeratorReports(Set<Long> userRegions, Set<Long> userCategories, Long userId, Pageable pageable);

    Page<Report> getRegionalModeratorReports(Set<Long> userRegions, Long userId, Pageable pageable);

    Page<Report> findAllByUserId(Long userId, Pageable pageable);

}
