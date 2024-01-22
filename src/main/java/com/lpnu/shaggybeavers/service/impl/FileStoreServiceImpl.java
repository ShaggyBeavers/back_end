package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.exception.FileException;
import com.lpnu.shaggybeavers.model.EntityWithId;
import com.lpnu.shaggybeavers.model.FileEntity;
import com.lpnu.shaggybeavers.service.FileStoreService;
import jakarta.validation.constraints.NotNull;
import org.springframework.content.commons.store.ContentStore;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;

public abstract class FileStoreServiceImpl
        <ENTITY extends FileEntity<CONTENT_ID> & EntityWithId<ID>, CONTENT_ID extends Serializable, ID>
        extends CRUDServiceImpl<ENTITY, ID> implements FileStoreService<ENTITY, CONTENT_ID, ID> {

    protected abstract ContentStore<ENTITY, CONTENT_ID> getContentStore();

    @Override
    @Transactional
    public void uploadFile(@NotNull ENTITY entity, @NotNull File file) {
        try (InputStream content = new FileInputStream(file)) {
            getContentStore().setContent(entity, content);
        } catch (Exception e) {
            throw new FileException("Error uploading file: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Resource downloadFile(@NotNull ENTITY entity) {
        try (InputStream content = getContentStore().getContent(entity)) {
            return new ByteArrayResource(content.readAllBytes());
        } catch (Exception e) {
            throw new FileException("Error downloading file: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteFile(@NotNull ENTITY entity) {
        getContentStore().unsetContent(entity);
    }

    @Override
    @Transactional
    public void updateFile(@NotNull ENTITY entity, @NotNull File file) {
        this.deleteFile(entity);
        this.uploadFile(entity, file);
    }

}
