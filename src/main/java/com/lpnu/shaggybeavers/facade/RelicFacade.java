package com.lpnu.shaggybeavers.facade;


import com.lpnu.shaggybeavers.domain.RelicStatus;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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

    private final RelicFileFacade relicFileFacade;

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
        var creationPlace = (relicCreateEditDTO.getCreationPlaceId() != null) ? regionFacade.findById((relicCreateEditDTO.getCreationPlaceId())) : null;
        relic.setCreationPlace(creationPlace);
        var museum = (relicCreateEditDTO.getMuseumId() != null) ? museumFacade.findById((relicCreateEditDTO.getMuseumId())) : null;
        relic.setMuseum(museum);
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
    public Long createRelic(RelicCreateEditDTO relicCreateEditDTO) {
        Relic relic = relicFactory.toRelic(relicCreateEditDTO);
        relic.setRegion(regionFacade.findById((relicCreateEditDTO.getRegionId())));
        var creationPlace = (relicCreateEditDTO.getCreationPlaceId() != null) ? regionFacade.findById((relicCreateEditDTO.getCreationPlaceId())) : null;
        relic.setCreationPlace(creationPlace);
        var museum = (relicCreateEditDTO.getMuseumId() != null) ? museumFacade.findById((relicCreateEditDTO.getMuseumId())) : null;
        relic.setMuseum(museum);
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
        return relic.getId();
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
    public void uploadFiles(Long relicId, List<MultipartFile> multipartFiles) {
        multipartFiles.forEach(multipartFile -> {
            try {
                relicFileFacade.createRelicFile(relicService.findById(relicId), FileUtil.toFile(multipartFile));
            } catch (IOException e) {
                throw new FileException("Failed uploading file: " + e.getMessage());
            }
        });
    }

    @Transactional
    public List<FileDTO> downloadFiles(Long relicId) {
        List<RelicFile> relicFiles = relicFileFacade.findAllByRelicId(relicId);
        return relicFiles.stream()
                .map(relicFile -> relicFactory.toFileDTO(relicFileFacade.downloadFile(relicFile), relicFile.getExtension(), relicId))
                .toList();
    }

    @Transactional
    public void deleteFiles(Long relicId) {
        relicFileFacade.deleteAllByRelicId(relicId);
    }

    @Transactional
    public void updateFiles(Long relicId, List<MultipartFile> multipartFiles) {
        deleteFiles(relicId);
        uploadFiles(relicId, multipartFiles);
    }

    @Transactional
    public List<FileDTO> downloadFiles(DownloadFilesDTO dto) {
        return dto.getEntityIds().stream()
                .map(relicId -> {
                    List<RelicFile> relicFiles = relicFileFacade.findAllByRelicId(relicId);
                    if (relicFiles.isEmpty()) {
                        return null;
                    }
                    return relicFiles.get(0);
                })
                .filter(Objects::nonNull)
                .map(relicFile -> relicFactory.toFileDTO(relicFileFacade.downloadFile(relicFile), relicFile.getExtension(), relicFile.getRelic().getId()))
                .toList();
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
    public void delete(Long relicId) {
        Relic relic = relicService.findById(relicId);

//        switch (RoleEnum.valueOf(currentUser.getRole().getName())) {
//            case ADMIN -> relicService.delete(relic);
//            case REGIONAL_MODERATOR, MODERATOR -> {
//                relic.setDeleted(true);
//                relicService.update(relic);
//            }
//        }
        relic.setDeleted(true);
    }

    @Transactional
    public void restore(Long relicId) {
        Relic relic = relicService.findByIdAbsolute(relicId);
        relic.setDeleted(false);
    }

}
