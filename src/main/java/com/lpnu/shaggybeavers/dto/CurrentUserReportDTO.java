package com.lpnu.shaggybeavers.dto;

import com.lpnu.shaggybeavers.domain.ReportStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CurrentUserReportDTO {

    private String name;

    private ReportStatus status;

    private List<ReportCategoryDTO> categoriesDTO;

}