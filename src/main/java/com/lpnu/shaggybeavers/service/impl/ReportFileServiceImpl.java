package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.ReportFile;
import com.lpnu.shaggybeavers.repository.ReportFileContentStore;
import com.lpnu.shaggybeavers.repository.ReportFileRepository;
import com.lpnu.shaggybeavers.service.ReportFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.content.commons.store.ContentStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportFileServiceImpl extends FileStoreServiceImpl<ReportFile, String, Long> implements ReportFileService {

    private final ReportFileRepository repository;

    private final ReportFileContentStore contentStore;

    @Override
    protected JpaRepository<ReportFile, Long> getRepository() {
        return this.repository;
    }

    @Override
    protected ContentStore<ReportFile, String> getContentStore() {
        return this.contentStore;
    }

    @Override
    public List<ReportFile> findAllByReportId(Long reportId) {
        return repository.findAllByReportId(reportId);
    }

}
