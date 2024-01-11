package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.RecoveredRelicInfoCreateEditDTO;
import com.lpnu.shaggybeavers.factory.RecoveredRelicInfoFactory;
import com.lpnu.shaggybeavers.model.RecoveredRelicInfo;
import com.lpnu.shaggybeavers.service.RecoveredRelicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecoveredRelicInfoFacade {

    private final RecoveredRelicInfoFactory recoveredRelicInfoFactory;

    private final RecoveredRelicInfoService recoveredRelicInfoService;

    public RecoveredRelicInfo findById(Long id) { return recoveredRelicInfoService.findById(id); }

    public void save(RecoveredRelicInfo recoveredRelicInfo) { recoveredRelicInfoService.save(recoveredRelicInfo); }

    public RecoveredRelicInfo toRecoveredRelicInfo(RecoveredRelicInfoCreateEditDTO recoveredRelicInfoCreateEditDTO) {
        return recoveredRelicInfoFactory.toRecoveredRelicInfo(recoveredRelicInfoCreateEditDTO);
    }

    public RecoveredRelicInfo update(Long id, RecoveredRelicInfoCreateEditDTO recoveredRelicInfoCreateEditDTO) {
        return recoveredRelicInfoFactory.update(recoveredRelicInfoService.findById(id), recoveredRelicInfoCreateEditDTO);
    }
}
