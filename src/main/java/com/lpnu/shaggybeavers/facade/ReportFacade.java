package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.CurrentUserReportDTO;
import com.lpnu.shaggybeavers.factory.ReportFactory;
import com.lpnu.shaggybeavers.filter.ReportFilter;
import com.lpnu.shaggybeavers.model.Report;
import com.lpnu.shaggybeavers.service.ReportService;
import com.lpnu.shaggybeavers.specification.ReportSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportFacade {

    private final ReportFactory reportFactory;

    private final ReportService reportService;

    @Transactional
    public Page<CurrentUserReportDTO> getUserReports(Long userId, Pageable pageable){
        ReportFilter reportFilter = new ReportFilter();
        reportFilter.setUserId(userId);
        Specification<Report> specification = new ReportSpecification(reportFilter);
        Page<Report> reportPage = reportService.findAll(pageable, specification);
        return reportPage.map(reportFactory::toReportDTO);
    }

}
