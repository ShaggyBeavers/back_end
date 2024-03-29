package com.lpnu.shaggybeavers.facade;


import com.lpnu.shaggybeavers.domain.RelicStatus;
import com.lpnu.shaggybeavers.domain.RoleEnum;
import com.lpnu.shaggybeavers.dto.*;
import com.lpnu.shaggybeavers.exception.FileException;
import com.lpnu.shaggybeavers.exception.NotExistsObjectException;
import com.lpnu.shaggybeavers.factory.RelicFactory;
import com.lpnu.shaggybeavers.filter.RelicFilter;
import com.lpnu.shaggybeavers.model.*;
import com.lpnu.shaggybeavers.service.RelicService;
import com.lpnu.shaggybeavers.specification.RelicSpecification;
import com.lpnu.shaggybeavers.util.FileUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RelicFacade {

    private final RelicFactory relicFactory;

    private final RelicService relicService;

    private final RelicInfoFacade relicInfoFacade;

    private final LostRelicInfoFacade lostRelicInfoFacade;

    private final RecoveredRelicInfoFacade recoveredRelicInfoFacade;

    private final RegionFacade regionFacade;

    private final MuseumFacade museumFacade;

    private final RelicPropertyFacade relicPropertyFacade;

    private final RelicCategoryFacade relicCategoryFacade;

    @Transactional
    public RelicDTO getRelicById(Long relicId) {
        return relicFactory.toRelicDTO(relicService.findById(relicId));
    }

    @Transactional
    public Page<RelicCatalogDTO> getCatalog(Pageable pageable) {
        Page<Relic> relicPage = relicService.findAll(pageable);
        return relicPage.map(relicFactory::toRelicCatalogDTO);
    }

    @Transactional
    public Page<RelicDTO> getRelicsByFilter(RelicFilter filter, Pageable pageable) {
        Specification<Relic> specification = new RelicSpecification(filter);
        Page<Relic> relicPage = relicService.findAll(specification, pageable);
        return relicPage.map(relicFactory::toRelicDTO);
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

    @Transactional
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

    @Transactional
    public void uploadFile(Long relicId, MultipartFile multipartFile) {
        try {
            relicService.uploadFile(relicService.findById(relicId), FileUtil.toFile(multipartFile));
        } catch (IOException ioe) {
            throw new FileException("Failed uploading file: " + ioe.getMessage());
        }
    }

    @Transactional
    public Resource downloadFile(Long relicId) {
        return relicService.downloadFile(relicService.findById(relicId));
    }

    @Transactional
    public void deleteFile(Long relicId) {
        relicService.deleteFile(relicService.findById(relicId));
    }

    @Transactional
    public void updateFile(Long relicId, MultipartFile multipartFile) {
        try {
            relicService.updateFile(relicService.findById(relicId), FileUtil.toFile(multipartFile));
        } catch (IOException ioe) {
            throw new FileException("Failed updating file: " + ioe.getMessage());
        }
    }

    @Transactional
    public Page<RelicCatalogDTO> getRelicsByName(String relicName, Pageable pageable) {
        Page<Relic> relicPage = relicService.findAllByNameContaining(relicName, pageable);
        return relicPage.map(relicFactory::toRelicCatalogDTO);
    }

    @Transactional
    public Relic findById(Long relicId) {
        return relicService.findById(relicId);
    }

    @Transactional
    public void delete(User currentUser, Long relicId) {
        Relic relic = relicService.findById(relicId);

        switch (RoleEnum.valueOf(currentUser.getRole().getName())) {
            case ADMIN -> relicService.delete(relic);
            case REGIONAL_MODERATOR, MODERATOR -> {
                relic.setDeleted(true);
                relicService.update(relic);
            }
        }
    }

}
