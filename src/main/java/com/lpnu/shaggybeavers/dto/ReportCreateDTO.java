package com.lpnu.shaggybeavers.dto;

import com.lpnu.shaggybeavers.domain.ReportStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ReportCreateDTO {

    public String name;

    public String description;

    public String mapLocation;

    public Set<Long> categoryIds;

    public ReportStatus status;

    public String infoReferences;

}
