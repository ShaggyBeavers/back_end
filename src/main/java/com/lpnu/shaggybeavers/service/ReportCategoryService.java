package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.ReportCategory;

import java.util.List;

public interface ReportCategoryService extends CRUDService<ReportCategory, Long> {

    List<ReportCategory> findAllByReportId(Long reportId);

}
