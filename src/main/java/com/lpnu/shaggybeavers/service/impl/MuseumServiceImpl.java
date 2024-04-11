package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.exception.AssociationException;
import com.lpnu.shaggybeavers.model.Museum;
import com.lpnu.shaggybeavers.repository.MuseumRepository;
import com.lpnu.shaggybeavers.service.LostRelicInfoService;
import com.lpnu.shaggybeavers.service.MuseumService;
import com.lpnu.shaggybeavers.service.RelicService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MuseumServiceImpl extends CRUDServiceImpl<Museum, Long> implements MuseumService {

    private final LostRelicInfoService lostRelicInfoService;

    private final RelicService relicService;

    private final MuseumRepository museumRepository;

    @Lazy
    public MuseumServiceImpl(LostRelicInfoService lostRelicInfoService, RelicService relicService, MuseumRepository museumRepository) {
        this.lostRelicInfoService = lostRelicInfoService;
        this.relicService = relicService;
        this.museumRepository = museumRepository;
    }

    @Override
    protected JpaRepository<Museum, Long> getRepository() {
        return this.museumRepository;
    }

    @Override
    @Transactional
    public Museum deleteById(Long museumId) {
        Museum museum = findById(museumId);

        if (lostRelicInfoService.existsByMuseumId(museumId)) {
            throw new AssociationException("The museum is associated with a lost relic info");
        }
        if (relicService.existsByMuseumId(museumId)) {
            throw new AssociationException("The museum is associated with a relic");
        }

        museumRepository.delete(museum);
        return museum;
    }

}
