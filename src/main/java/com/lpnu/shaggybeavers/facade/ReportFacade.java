package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.ReportCreateDTO;
import com.lpnu.shaggybeavers.dto.ReportReadDTO;
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
    public ReportReadDTO createReport(UserPrincipal userPrincipal, ReportCreateDTO reportCreateDTO) {
        Report report = reportService.save(reportFactory.toReport(reportCreateDTO));
        report.setUser(userPrincipal.getUser());

        reportCreateDTO.categoryIds
                .forEach(categoryId -> {
                    Category category = categoryFacade.findById(categoryId);
                    ReportCategory reportCategory = new ReportCategory();
                    reportCategory.setReport(report);
                    reportCategory.setCategory(category);
                    reportCategoryFacade.save(reportCategory);
                });

        ReportReadDTO reportReadDTO = new ReportReadDTO();
        reportReadDTO.setId(report.getId());
        return reportReadDTO;
    }

}
