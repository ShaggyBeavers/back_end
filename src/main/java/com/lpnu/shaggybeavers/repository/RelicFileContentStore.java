package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.RelicFile;
import org.springframework.content.commons.store.ContentStore;
import org.springframework.stereotype.Component;

@Component
public interface RelicFileContentStore extends ContentStore<RelicFile, String> {
}
