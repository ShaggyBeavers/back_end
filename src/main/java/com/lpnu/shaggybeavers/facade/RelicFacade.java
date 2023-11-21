package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.RelicCatalogDTO;
import com.lpnu.shaggybeavers.dto.RelicDTO;
import com.lpnu.shaggybeavers.factory.RelicFactory;
import com.lpnu.shaggybeavers.model.Relic;
import com.lpnu.shaggybeavers.service.RelicService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class RelicFacade {

    private final RelicFactory relicFactory;

    private final RelicService relicService;

    private final StorageFacade storageFacade;

    public String uploadFile(MultipartFile multipartFile, Long relicId) {
        String url = storageFacade.uploadFile(multipartFile);
        Relic relic = relicService.findById(relicId);
        relic.setImageUrl(url);
        relicService.update(relic);
        return url;
    }

    public String downloadFile(Long relicId) {
        return relicService.findById(relicId).getImageUrl();
    }

    public RelicDTO getRelicById(Long relicId) {
        return relicFactory.toRelicDTO(relicService.findById(relicId));
    }

    @Transactional
    public Page<RelicCatalogDTO> getCatalog(Pageable pageable) {
        Page<Relic> relicPage = relicService.findAll(pageable);
        return relicPage.map(relicFactory::toRelicCatalogDTO);
    }

}
