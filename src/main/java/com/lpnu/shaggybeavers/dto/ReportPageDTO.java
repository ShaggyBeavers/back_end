package com.lpnu.shaggybeavers.dto;

import com.lpnu.shaggybeavers.domain.ReportStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReportPageDTO {

    private String name;

    private ReportStatus status;

    private List<CategoryDTO> categoriesDTO;

    private String userEmail;

}