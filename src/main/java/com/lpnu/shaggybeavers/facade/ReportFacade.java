package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.ReportDTO;
import com.lpnu.shaggybeavers.factory.ReportFactory;
import com.lpnu.shaggybeavers.model.Category;
import com.lpnu.shaggybeavers.model.Report;
import com.lpnu.shaggybeavers.model.ReportCategory;
import com.lpnu.shaggybeavers.security.UserPrincipal;
import com.lpnu.shaggybeavers.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ReportFacade {

    private final ReportFactory reportFactory;

    private final ReportService reportService;

    private final CategoryFacade categoryFacade;

    private final ReportCategoryFacade reportCategoryFacade;

    @Transactional
    public Long createReport(UserPrincipal userPrincipal, ReportDTO reportDTO) {
        Report report = reportService.save(reportFactory.toReport(reportDTO));
        report.setUser(userPrincipal.getUser());

        reportDTO.categoryIds
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
