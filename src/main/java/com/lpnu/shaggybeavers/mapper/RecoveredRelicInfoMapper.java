package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.RecoveredRelicInfoCreateEditDTO;
import com.lpnu.shaggybeavers.model.RecoveredRelicInfo;

public interface RecoveredRelicInfoMapper {

    RecoveredRelicInfo toRecoveredRelicInfo(RecoveredRelicInfoCreateEditDTO recoveredRelicInfoCreateEditDTO);
}
