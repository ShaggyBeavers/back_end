package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.domain.RelicStatus;
import com.lpnu.shaggybeavers.dto.FavoriteRelicDTO;
import com.lpnu.shaggybeavers.dto.RelicCatalogDTO;
import com.lpnu.shaggybeavers.dto.RelicDTO;
import com.lpnu.shaggybeavers.exception.NotExistsObjectException;
import com.lpnu.shaggybeavers.factory.RelicFactory;
import com.lpnu.shaggybeavers.filter.RelicFilter;
import com.lpnu.shaggybeavers.model.Relic;
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
