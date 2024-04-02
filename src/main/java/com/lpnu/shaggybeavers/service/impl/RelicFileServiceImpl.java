package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.RelicFile;
import com.lpnu.shaggybeavers.repository.RelicFileContentStore;
import com.lpnu.shaggybeavers.repository.RelicFileRepository;
import com.lpnu.shaggybeavers.service.RelicFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.content.commons.store.ContentStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RelicFileServiceImpl extends FileStoreServiceImpl<RelicFile, String, Long> implements RelicFileService {

    private final RelicFileRepository repository;

    private final RelicFileContentStore contentStore;

    @Override
    protected JpaRepository<RelicFile, Long> getRepository() {
        return this.repository;
    }

    @Override
    protected ContentStore<RelicFile, String> getContentStore() {
        return this.contentStore;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RelicFile> findAllByRelicId(Long relicId) {
        return repository.findAllByRelicId(relicId);
    }

}
