package com.lpnu.shaggybeavers.facade;


import com.lpnu.shaggybeavers.dto.*;

import com.lpnu.shaggybeavers.domain.RelicStatus;
import com.lpnu.shaggybeavers.dto.FavoriteRelicDTO;
import com.lpnu.shaggybeavers.dto.RelicCatalogDTO;
import com.lpnu.shaggybeavers.dto.RelicDTO;
import com.lpnu.shaggybeavers.exception.NotExistsObjectException;
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

    private final RegionFacade regionFacade;

    private final MuseumFacade museumFacade;

    private final RelicPropertyFacade relicPropertyFacade;

    private final RelicCategoryFacade relicCategoryFacade;

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
        relic.setRegion(regionFacade.findById((relicCreateEditDTO.getRegionId())));
        relic.setCreationPlace(regionFacade.findById((relicCreateEditDTO.getCreationPlaceId())));
        relic.setMuseum(museumFacade.findById((relicCreateEditDTO.getMuseumId())));
        relicService.save(relic);

        relicCategoryFacade.updateRelicCategories(relicCreateEditDTO.getRelicCategoryIds(), relic);
        relicPropertyFacade.updateRelicProperties(relicCreateEditDTO.getRelicPropertyIds(), relic, relicCreateEditDTO.getPropertyValues());

        RelicInfoCreateEditDTO relicInfoCreateEditDTO = relicCreateEditDTO.getRelicInfoCreateEditDTO();
        RelicInfo relicInfo = relicInfoFacade.update(relic.getRelicInfo().getId(), relicInfoCreateEditDTO);

        LostRelicInfoCreateEditDTO lostRelicInfoCreateEditDTO = relicInfoCreateEditDTO.getLostRelicInfoCreateEditDTO();
        if (lostRelicInfoCreateEditDTO != null) {
            LostRelicInfo lostRelicInfo = relicInfo.getLostRelicInfo();
            if (lostRelicInfo == null) {
                lostRelicInfoFacade.create(lostRelicInfoCreateEditDTO, relicInfo);
            }
            else {
                lostRelicInfoFacade.update(lostRelicInfo.getId(), lostRelicInfoCreateEditDTO);
            }
        }

        RecoveredRelicInfoCreateEditDTO recoveredRelicInfoCreateEditDTO = relicInfoCreateEditDTO.getRecoveredRelicInfoCreateEditDTO();
        if (recoveredRelicInfoCreateEditDTO != null) {
            RecoveredRelicInfo recoveredRelicInfo = relicInfo.getRecoveredRelicInfo();
            if (recoveredRelicInfo == null) {
                recoveredRelicInfoFacade.create(recoveredRelicInfoCreateEditDTO, relicInfo);
            }
            else {
                recoveredRelicInfoFacade.update(recoveredRelicInfo.getId(), recoveredRelicInfoCreateEditDTO);
            }
        }
    }

    @Transactional
    public void createRelic(RelicCreateEditDTO relicCreateEditDTO) {
        Relic relic = relicFactory.toRelic(relicCreateEditDTO);
        relic.setRegion(regionFacade.findById((relicCreateEditDTO.getRegionId())));
        relic.setCreationPlace(regionFacade.findById((relicCreateEditDTO.getCreationPlaceId())));
        relic.setMuseum(museumFacade.findById((relicCreateEditDTO.getMuseumId())));
        relic = relicService.save(relic);

        relicCategoryFacade.updateRelicCategories(relicCreateEditDTO.getRelicCategoryIds(), relic);
        relicPropertyFacade.updateRelicProperties(relicCreateEditDTO.getRelicPropertyIds(), relic, relicCreateEditDTO.getPropertyValues());

        RelicInfo relicInfo = relicInfoFacade.create(relicCreateEditDTO.getRelicInfoCreateEditDTO(), relic);

        RecoveredRelicInfoCreateEditDTO recoveredRelicInfoCreateEditDTO = relicCreateEditDTO.getRelicInfoCreateEditDTO().getRecoveredRelicInfoCreateEditDTO();
        if (recoveredRelicInfoCreateEditDTO != null) {
            recoveredRelicInfoFacade.create(recoveredRelicInfoCreateEditDTO, relicInfo);
        }

        LostRelicInfoCreateEditDTO lostRelicInfoCreateEditDTO = relicCreateEditDTO.getRelicInfoCreateEditDTO().getLostRelicInfoCreateEditDTO();
        if (lostRelicInfoCreateEditDTO != null) {
            lostRelicInfoFacade.create(lostRelicInfoCreateEditDTO, relicInfo);
        }
    }

    public void changeFavoriteRelic() {
        Relic currentFavoriteRelic = relicService.getCurrentFavoriteRelic();
        Relic newFavoriteRelic = relicService.getNewFavoriteRelic();
        if (currentFavoriteRelic != null) {
            currentFavoriteRelic.setFavorite(false);
            relicService.update(currentFavoriteRelic);
        }
        if (newFavoriteRelic == null) {
            throw new NotExistsObjectException("There is no new relic with image at the moment.");
        }
        newFavoriteRelic.setFavorite(true);
        relicService.update(newFavoriteRelic);
    }

    @Transactional
    public FavoriteRelicDTO getFavoriteRelic() {
        return relicFactory.toFavoriteRelicDTO(relicService.getCurrentFavoriteRelic());
    }

    @Transactional
    public Long countByStatuses(List<RelicStatus> statuses) {
        return relicService.countByStatuses(statuses);
    }

}
