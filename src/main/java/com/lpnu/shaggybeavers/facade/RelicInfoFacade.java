package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.RelicInfoCreateEditDTO;
import com.lpnu.shaggybeavers.factory.RelicInfoFactory;
import com.lpnu.shaggybeavers.model.Relic;
import com.lpnu.shaggybeavers.model.RelicInfo;
import com.lpnu.shaggybeavers.service.RelicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RelicInfoFacade {

    private final RelicInfoFactory relicInfoFactory;

    private final RelicInfoService relicInfoService;

    public RelicInfo findById(Long id) { return relicInfoService.findById(id); }

    public void save(RelicInfo relicInfo) { relicInfoService.save(relicInfo);
    }

    public RelicInfo save(RelicInfoCreateEditDTO relicInfoCreateEditDTO, Relic relic) {
        RelicInfo relicInfo = relicInfoFactory.toRelicInfo(relicInfoCreateEditDTO);
        relicInfo.setRelic(relic);
        return relicInfoService.save(relicInfo);
    }

    public RelicInfo update(Long id, RelicInfoCreateEditDTO relicInfoCreateEditDTO) {
        return relicInfoFactory.update(relicInfoService.findById(id), relicInfoCreateEditDTO);
    }
}
