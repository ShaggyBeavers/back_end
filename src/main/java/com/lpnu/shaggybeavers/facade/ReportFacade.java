package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.ReportDTO;
import com.lpnu.shaggybeavers.factory.ReportFactory;
import com.lpnu.shaggybeavers.filter.ReportByUserFilter;
import com.lpnu.shaggybeavers.model.Relic;
import com.lpnu.shaggybeavers.model.Report;
import com.lpnu.shaggybeavers.service.ReportService;
import com.lpnu.shaggybeavers.specification.RelicSpecification;
import com.lpnu.shaggybeavers.specification.ReportSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReportFacade {

    private final ReportFactory reportFactory;

    private final ReportService reportService;

    @Transactional
    public Page<ReportDTO> getUserReports(Long userId, Pageable pageable){
        ReportByUserFilter reportByUserFilter = new ReportByUserFilter();
        reportByUserFilter.setUserId(userId);
        Specification<Report> specification = new ReportSpecification(reportByUserFilter);
        Page<Report> reportPage = reportService.findAll(pageable, specification);
        return reportPage.map(reportFactory::toReportDTO);
    }

}
