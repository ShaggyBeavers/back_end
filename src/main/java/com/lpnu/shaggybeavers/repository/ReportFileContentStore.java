package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.ReportFile;
import org.springframework.content.commons.store.ContentStore;
import org.springframework.stereotype.Component;

@Component
public interface ReportFileContentStore extends ContentStore<ReportFile, String> {
}
