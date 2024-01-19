package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.EntityWithId;
import com.lpnu.shaggybeavers.model.FileEntity;
import org.springframework.core.io.Resource;

import java.io.File;

public interface FileStoreService <ENTITY extends FileEntity<CONTENT_ID> & EntityWithId<ID>, CONTENT_ID, ID> extends CRUDService<ENTITY, ID> {

    void uploadFile(ENTITY entity, File file);

    Resource downloadFile(ENTITY entity);

    void deleteFile(ENTITY entity);

    void updateFile(ENTITY entity, File file);

}
