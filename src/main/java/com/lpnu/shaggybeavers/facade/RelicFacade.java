package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.*;
import com.lpnu.shaggybeavers.factory.RelicFactory;
import com.lpnu.shaggybeavers.filter.RelicFilter;
import com.lpnu.shaggybeavers.model.LostRelicInfo;
import com.lpnu.shaggybeavers.model.RecoveredRelicInfo;
import com.lpnu.shaggybeavers.model.Relic;
import com.lpnu.shaggybeavers.model.RelicInfo;
import com.lpnu.shaggybeavers.service.RelicService;
import com.lpnu.shaggybeavers.specification.RelicSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RelicFacade {

    private final RelicFactory relicFactory;

    private final RelicService relicService;

    private final StorageFacade storageFacade;

    private final RelicInfoFacade relicInfoFacade;

    private final LostRelicInfoFacade lostRelicInfoFacade;

    private final RecoveredRelicInfoFacade recoveredRelicInfoFacade;

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

    @Transactional
    public List<RelicDTO> getRelicsByFilter(RelicFilter filter) {
        Specification<Relic> specification = new RelicSpecification(filter);
        List<Relic> relics = relicService.findAll(specification);
        return relicFactory.toRelicDTOList(relics);
    }

    @Transactional
    public void editRelic(Long relicId, RelicCreateEditDTO relicCreateEditDTO) {
        Relic relic = relicFactory.update(relicService.findById(relicId), relicCreateEditDTO);
        // optional if relic or relicInfo not found?
        RelicInfoCreateEditDTO relicInfoCreateEditDTO = relicCreateEditDTO.getRelicInfoCreateEditDTO();
        RelicInfo relicInfo = relicInfoFacade.update(relic.getRelicInfo().getId(), relicInfoCreateEditDTO);

        LostRelicInfoCreateEditDTO lostRelicInfoCreateEditDTO = relicInfoCreateEditDTO.getLostRelicInfoCreateEditDTO();
        if (lostRelicInfoCreateEditDTO != null) {
            LostRelicInfo lostRelicInfo = relicInfo.getLostRelicInfo();
            if (lostRelicInfo == null) {
                lostRelicInfo = lostRelicInfoFacade.toLostRelicInfo(lostRelicInfoCreateEditDTO);
                lostRelicInfo.setRelicInfo(relicInfo);
            }
            else {
                lostRelicInfo = lostRelicInfoFacade.update(lostRelicInfo.getId(), lostRelicInfoCreateEditDTO);
            }
            lostRelicInfoFacade.save(lostRelicInfo);
        }

        RecoveredRelicInfoCreateEditDTO recoveredRelicInfoCreateEditDTO = relicInfoCreateEditDTO.getRecoveredRelicInfoCreateEditDTO();
        if (recoveredRelicInfoCreateEditDTO != null) {
            RecoveredRelicInfo recoveredRelicInfo = relicInfo.getRecoveredRelicInfo();
            if (recoveredRelicInfo == null) {
                recoveredRelicInfo = recoveredRelicInfoFacade.toRecoveredRelicInfo(recoveredRelicInfoCreateEditDTO);
                recoveredRelicInfo.setRelicInfo(relicInfo);
            }
            else {
                recoveredRelicInfo = recoveredRelicInfoFacade.update(recoveredRelicInfo.getId(), recoveredRelicInfoCreateEditDTO);
            }
            recoveredRelicInfoFacade.save(recoveredRelicInfo);
        }
        relicInfoFacade.save(relicInfo);
        relicService.save(relic);
    }

    @Transactional
    public void createRelic(RelicCreateEditDTO relicCreateEditDTO) {
        Relic relic = relicService.save(relicFactory.toRelic(relicCreateEditDTO));
        RelicInfo relicInfo = relicInfoFacade.save(relicCreateEditDTO.getRelicInfoCreateEditDTO(), relic);

        RecoveredRelicInfoCreateEditDTO recoveredRelicInfoCreateEditDTO = relicCreateEditDTO.getRelicInfoCreateEditDTO().getRecoveredRelicInfoCreateEditDTO();
        if (recoveredRelicInfoCreateEditDTO != null) {
            RecoveredRelicInfo recoveredRelicInfo = recoveredRelicInfoFacade.toRecoveredRelicInfo(recoveredRelicInfoCreateEditDTO);
            recoveredRelicInfo.setRelicInfo(relicInfo);
            recoveredRelicInfoFacade.save(recoveredRelicInfo);
        }

        LostRelicInfoCreateEditDTO lostRelicInfoCreateEditDTO = relicCreateEditDTO.getRelicInfoCreateEditDTO().getLostRelicInfoCreateEditDTO();
        if (lostRelicInfoCreateEditDTO != null) {
            LostRelicInfo lostRelicInfo = lostRelicInfoFacade.toLostRelicInfo(lostRelicInfoCreateEditDTO);
            lostRelicInfo.setRelicInfo(relicInfo);
            lostRelicInfoFacade.save(lostRelicInfo);
        }
    }
}
