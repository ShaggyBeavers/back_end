package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.RelicInfoCreateEditDTO;
import com.lpnu.shaggybeavers.factory.RelicInfoFactory;
import com.lpnu.shaggybeavers.model.Relic;
import com.lpnu.shaggybeavers.model.RelicInfo;
import com.lpnu.shaggybeavers.service.RelicInfoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RelicInfoFacade {

    private final RelicInfoFactory relicInfoFactory;

    private final RelicInfoService relicInfoService;

    private final TechniqueFacade techniqueFacade;

    private final HistoricalPeriodFacade historicalPeriodFacade;


    @Transactional
    public RelicInfo create(RelicInfoCreateEditDTO relicInfoCreateEditDTO, Relic relic) {
        RelicInfo relicInfo = relicInfoFactory.toRelicInfo(relicInfoCreateEditDTO);
        relicInfo.setRelic(relic);
        relicInfo.setTechnique(techniqueFacade.findById(relicInfoCreateEditDTO.getTechniqueId()));
        relicInfo.setHistoricalPeriod(historicalPeriodFacade.findById(relicInfoCreateEditDTO.getHistoricalPeriodId()));
        return relicInfoService.save(relicInfo);
    }

    @Transactional
    public RelicInfo update(Long id, RelicInfoCreateEditDTO relicInfoCreateEditDTO) {
        RelicInfo relicInfo = relicInfoFactory.update(relicInfoService.findById(id), relicInfoCreateEditDTO);
        relicInfo.setTechnique(techniqueFacade.findById(relicInfoCreateEditDTO.getTechniqueId()));
        relicInfo.setHistoricalPeriod(historicalPeriodFacade.findById(relicInfoCreateEditDTO.getHistoricalPeriodId()));
        return relicInfoService.save(relicInfo);
    }
}
