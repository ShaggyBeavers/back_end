package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.ReportCategoryDTO;
import com.lpnu.shaggybeavers.model.ReportCategory;
import org.mapstruct.Mapping;

public interface ReportCategoryMapper {

    @Mapping(source = "category.categoryName", target = "name")
    @Mapping(source = "category.id", target = "id")
    ReportCategoryDTO toReportCategoryDTO(ReportCategory reportCategory);

}
