package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.CategoryDTO;
import com.lpnu.shaggybeavers.model.ReportCategory;
import org.mapstruct.Mapping;

public interface ReportCategoryMapper {

    @Mapping(source = "category.categoryName", target = "categoryName")
    @Mapping(source = "category.id", target = "id")
    CategoryDTO toCategoryDTO(ReportCategory reportCategory);

}
