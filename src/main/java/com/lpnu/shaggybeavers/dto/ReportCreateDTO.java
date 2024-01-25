package com.lpnu.shaggybeavers.dto;

import com.lpnu.shaggybeavers.domain.ReportStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ReportCreateDTO {

    private String name;

    private String description;

    private String mapLocation;

    private Set<Long> categoryIds;

    private ReportStatus status;

    private String infoReferences;

}
