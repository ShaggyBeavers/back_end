package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.ReportFile;

import java.util.List;

public interface ReportFileService extends FileStoreService<ReportFile, String, Long> {
    List<ReportFile> findAllByReportId(Long reportId);

}
