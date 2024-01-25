package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.CurrentUserReportDTO;
import com.lpnu.shaggybeavers.dto.ReportCreateDTO;
import com.lpnu.shaggybeavers.factory.ReportFactory;
import com.lpnu.shaggybeavers.filter.ReportFilter;
import com.lpnu.shaggybeavers.model.Category;
import com.lpnu.shaggybeavers.model.Report;
import com.lpnu.shaggybeavers.model.ReportCategory;
import com.lpnu.shaggybeavers.security.UserPrincipal;
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
  
    private final CategoryFacade categoryFacade;

    private final ReportCategoryFacade reportCategoryFacade;

    @Transactional
    public Page<CurrentUserReportDTO> getUserReports(Long userId, Pageable pageable) {
        ReportFilter reportFilter = new ReportFilter();
        reportFilter.setUserId(userId);
        Specification<Report> specification = new ReportSpecification(reportFilter);
        Page<Report> reportPage = reportService.findAll(pageable, specification);
        return reportPage.map(reportFactory::toReportDTO);
    }

    @Transactional
    public Long createReport(UserPrincipal userPrincipal, ReportCreateDTO reportCreateDTO) {
        Report report = reportService.save(reportFactory.toReport(reportCreateDTO));
        report.setUser(userPrincipal.getUser());

        reportCreateDTO.getCategoryIds()
                .forEach(categoryId -> {
                    Category category = categoryFacade.findById(categoryId);
                    ReportCategory reportCategory = new ReportCategory();
                    reportCategory.setReport(report);
                    reportCategory.setCategory(category);
                    reportCategoryFacade.save(reportCategory);
                });

        return report.getId();
    }

}