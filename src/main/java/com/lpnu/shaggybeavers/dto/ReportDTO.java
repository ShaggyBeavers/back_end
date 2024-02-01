package com.lpnu.shaggybeavers.dto;

import com.lpnu.shaggybeavers.domain.ReportStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ReportDTO {

    private String userEmail;

    private String userId;

    private Date submissionDate;

    private String mapLocation;

    private String description;

    private ReportStatus status;

    private String comment;

    private String infoReferences;

    private String name;

    private String imageUrl;

    private List<CategoryDTO> categoryDTOs;

    private Long regionId;

}
