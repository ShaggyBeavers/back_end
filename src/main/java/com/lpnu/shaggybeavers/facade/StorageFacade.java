package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.StorageFactory;
import com.lpnu.shaggybeavers.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@Component
@RequiredArgsConstructor
public class StorageFacade {

    private final StorageFactory storageFactory;

    private final StorageService storageService;

    public URL uploadFile(MultipartFile multipartFile) throws IOException {
        return storageService.uploadFile(multipartFile);
    }

    public URL downloadFile(String fileName) {
        return storageService.downloadFile(fileName);
    }
}
