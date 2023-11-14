package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.StorageFactory;
import com.lpnu.shaggybeavers.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class StorageFacade {

    private final StorageFactory storageFactory;

    private final StorageService storageService;

    public String uploadFile(MultipartFile multipartFile) {
        return storageService.uploadFile(multipartFile);
    }
}
