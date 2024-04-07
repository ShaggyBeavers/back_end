package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.LostRelicInfoCreateEditDTO;
import com.lpnu.shaggybeavers.factory.LostRelicInfoFactory;
import com.lpnu.shaggybeavers.model.LostRelicInfo;
import com.lpnu.shaggybeavers.model.RelicInfo;
import com.lpnu.shaggybeavers.service.LostRelicInfoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LostRelicInfoFacade {

    private final LostRelicInfoFactory lostRelicInfoFactory;

    private final LostRelicInfoService lostRelicInfoService;

    private final MuseumFacade museumFacade;

    @Transactional
    public void create(LostRelicInfoCreateEditDTO lostRelicInfoCreateEditDTO, RelicInfo relicInfo) {
        LostRelicInfo lostRelicInfo = lostRelicInfoFactory.toLostRelicInfo(lostRelicInfoCreateEditDTO);
        lostRelicInfo.setRelicInfo(relicInfo);
        var museum = (lostRelicInfoCreateEditDTO.getMuseumId() != null) ? museumFacade.findById((lostRelicInfoCreateEditDTO.getMuseumId())) : null;
        lostRelicInfo.setMuseum(museum);
        lostRelicInfoService.save(lostRelicInfo);
    }

    @Transactional
    public void update(Long id, LostRelicInfoCreateEditDTO lostRelicInfoCreateEditDTO) {
        LostRelicInfo lostRelicInfo = lostRelicInfoFactory.update(lostRelicInfoService.findById(id), lostRelicInfoCreateEditDTO);
        var museum = (lostRelicInfoCreateEditDTO.getMuseumId() != null) ? museumFacade.findById((lostRelicInfoCreateEditDTO.getMuseumId())) : null;
        lostRelicInfo.setMuseum(museum);
        lostRelicInfoService.save(lostRelicInfo);
    }
}
