package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.FileDTO;
import com.lpnu.shaggybeavers.dto.ReportCreateDTO;
import com.lpnu.shaggybeavers.dto.ReportDTO;
import com.lpnu.shaggybeavers.dto.ReportPageDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportFactory {

    private final EntityMapper entityMapper;

    public Report toReport(ReportCreateDTO reportCreateDTO) {
        return entityMapper.toReport(reportCreateDTO);
    }

    public ReportDTO toReportDTO(Report report) { return entityMapper.toReportDTO(report); }

    public ReportPageDTO toReportPageDTO(Report report) { return entityMapper.toReportPageDTO(report); }

    public FileDTO toFileDTO(Resource file, String extension, Long entityId) {
        return entityMapper.toFileDTO(file, extension, entityId);
    }
}