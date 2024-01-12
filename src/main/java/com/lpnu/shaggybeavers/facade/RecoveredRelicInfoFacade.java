package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.RecoveredRelicInfoCreateEditDTO;
import com.lpnu.shaggybeavers.factory.RecoveredRelicInfoFactory;
import com.lpnu.shaggybeavers.model.RecoveredRelicInfo;
import com.lpnu.shaggybeavers.model.RelicInfo;
import com.lpnu.shaggybeavers.service.RecoveredRelicInfoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecoveredRelicInfoFacade {

    private final RecoveredRelicInfoFactory recoveredRelicInfoFactory;

    private final RecoveredRelicInfoService recoveredRelicInfoService;

    @Transactional
    public void create(RecoveredRelicInfoCreateEditDTO recoveredRelicInfoCreateEditDTO, RelicInfo relicInfo) {
        RecoveredRelicInfo recoveredRelicInfo = recoveredRelicInfoFactory.toRecoveredRelicInfo(recoveredRelicInfoCreateEditDTO);
        recoveredRelicInfo.setRelicInfo(relicInfo);
        recoveredRelicInfoService.save(recoveredRelicInfo);
    }

    @Transactional
    public void update(Long id, RecoveredRelicInfoCreateEditDTO recoveredRelicInfoCreateEditDTO) {
        recoveredRelicInfoService.save(recoveredRelicInfoFactory.update(recoveredRelicInfoService.findById(id), recoveredRelicInfoCreateEditDTO));
    }
}
