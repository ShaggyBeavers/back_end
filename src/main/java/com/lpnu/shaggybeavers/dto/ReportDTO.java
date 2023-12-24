package com.lpnu.shaggybeavers.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReportDTO {

    private String name ;

    //status

    private List<ReportCategoryDTO> categoriesDTO;

}