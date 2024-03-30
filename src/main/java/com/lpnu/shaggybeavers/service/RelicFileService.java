package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.RelicFile;

import java.util.List;

public interface RelicFileService extends FileStoreService<RelicFile, String, Long> {

    List<RelicFile> findAllByRelicId(Long relicId);

}
