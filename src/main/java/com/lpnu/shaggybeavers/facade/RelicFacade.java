package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.RelicFactory;
import com.lpnu.shaggybeavers.service.RelicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@Component
@RequiredArgsConstructor
public class RelicFacade {

    private final RelicFactory relicFactory;

    private final RelicService relicService;

    private final StorageFacade storageFacade;

    public URL uploadFile(MultipartFile multipartFile, Long relicId) throws IOException {
        URL url = storageFacade.uploadFile(multipartFile);
        relicService.setImageUrl(url, relicId);
        return url;
    }

    public URL downloadFile(String fileName) {
        return storageFacade.downloadFile(fileName);
    }

}
