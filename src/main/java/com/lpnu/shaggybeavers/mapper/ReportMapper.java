package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.ReportDTO;
import com.lpnu.shaggybeavers.model.Report;
import org.mapstruct.Mapping;

public interface ReportMapper {

    @Mapping(source = "reportCategories", target = "categoriesDTO")
    ReportDTO toReportDTO(Report report);
}
