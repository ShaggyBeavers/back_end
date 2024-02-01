package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.ReportCreateDTO;
import com.lpnu.shaggybeavers.dto.ReportDTO;
import com.lpnu.shaggybeavers.dto.ReportPageDTO;
import com.lpnu.shaggybeavers.model.Report;
import org.mapstruct.Mapping;

public interface ReportMapper {

    Report toReport(ReportCreateDTO reportCreateDTO);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.email", target = "userEmail")
    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "reportCategories", target = "categoryDTOs")
    ReportDTO toReportDTO(Report report);

    @Mapping(source = "user.email", target = "userEmail")
    @Mapping(source = "reportCategories", target = "categoriesDTO")
    ReportPageDTO toReportPageDTO(Report report);
}