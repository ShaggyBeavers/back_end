package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.ReportCreateDTO;
import com.lpnu.shaggybeavers.model.Report;

public interface ReportMapper {

    Report toReport(ReportCreateDTO reportCreateDTO);

}
