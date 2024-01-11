package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.RecoveredRelicInfoCreateEditDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.RecoveredRelicInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecoveredRelicInfoFactory {

    private final EntityMapper entityMapper;

    public RecoveredRelicInfo toRecoveredRelicInfo(RecoveredRelicInfoCreateEditDTO recoveredRelicInfoCreateEditDTO) {
        return entityMapper.toRecoveredRelicInfo(recoveredRelicInfoCreateEditDTO);
    }

    public RecoveredRelicInfo update(RecoveredRelicInfo recoveredRelicInfo, RecoveredRelicInfoCreateEditDTO recoveredRelicInfoCreateEditDTO) {
        return entityMapper.update(recoveredRelicInfo, recoveredRelicInfoCreateEditDTO);
    }
}
