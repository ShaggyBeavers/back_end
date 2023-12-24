package com.lpnu.shaggybeavers.dto;

import com.lpnu.shaggybeavers.domain.ReportStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportCreateDTO {

    public String name;

    public String description;

    public String mapLocation;

    public List<Long> categoryIds;

    public ReportStatus status;

    public String infoReferences;

}
