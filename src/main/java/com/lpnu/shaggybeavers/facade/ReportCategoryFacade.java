package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.ReportCategoryFactory;
import com.lpnu.shaggybeavers.model.ReportCategory;
import com.lpnu.shaggybeavers.service.ReportCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportCategoryFacade {

    private final ReportCategoryFactory reportCategoryFactory;

    private final ReportCategoryService reportCategoryService;

    public void save(ReportCategory reportCategory) {
        reportCategoryService.save(reportCategory);
    }

}
