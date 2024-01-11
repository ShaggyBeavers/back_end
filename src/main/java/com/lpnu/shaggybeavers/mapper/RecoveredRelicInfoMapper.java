package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.RecoveredRelicInfoCreateEditDTO;
import com.lpnu.shaggybeavers.model.RecoveredRelicInfo;
import org.mapstruct.MappingTarget;

public interface RecoveredRelicInfoMapper {

    RecoveredRelicInfo toRecoveredRelicInfo(RecoveredRelicInfoCreateEditDTO recoveredRelicInfoCreateEditDTO);

    RecoveredRelicInfo update(@MappingTarget RecoveredRelicInfo recoveredRelicInfo, RecoveredRelicInfoCreateEditDTO recoveredRelicInfoCreateEditDTO);
}
