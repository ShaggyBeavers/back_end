package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.model.Report;
import com.lpnu.shaggybeavers.model.ReportFile;
import com.lpnu.shaggybeavers.service.ReportFileService;
import com.lpnu.shaggybeavers.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReportFileFacade {

    private final ReportFileService reportFileService;

    @Transactional
    public void createReportFile(Report report, File file) {
        ReportFile reportFile = new ReportFile();
        reportFile.setReport(report);
        reportFile.setExtension(FileUtil.getExtension(file));
        reportFileService.uploadFile(reportFile, file);
        reportFileService.save(reportFile);
    }

    @Transactional(readOnly = true)
    public List<ReportFile> findAllByReportId(Long reportId) {
        return reportFileService.findAllByReportId(reportId);
    }

    @Transactional(readOnly = true)
    public Resource downloadFile(ReportFile reportFile) {
        return reportFileService.downloadFile(reportFile);
    }

    @Transactional
    public void deleteAllByReportId(Long reportId) {
        List<ReportFile> reportFiles = reportFileService.findAllByReportId(reportId);
        reportFiles.forEach(reportFile -> {
            reportFileService.deleteFile(reportFile);
            reportFileService.delete(reportFile);
        });
    }

}
