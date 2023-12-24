package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.ReportDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportFactory {

    private final EntityMapper entityMapper;

    public Report toReport(ReportDTO reportDTO) {
        return entityMapper.toReport(reportDTO);
    }

}
