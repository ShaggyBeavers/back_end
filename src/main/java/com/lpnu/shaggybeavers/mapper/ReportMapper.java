package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.ReportDTO;
import com.lpnu.shaggybeavers.model.Report;

public interface ReportMapper {

    Report toReport(ReportDTO reportCreateDTO);

}
