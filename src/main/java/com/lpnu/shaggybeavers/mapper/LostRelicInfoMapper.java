package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.LostRelicInfoCreateEditDTO;
import com.lpnu.shaggybeavers.model.LostRelicInfo;
import org.mapstruct.MappingTarget;

public interface LostRelicInfoMapper {

    LostRelicInfo toLostRelicInfo(LostRelicInfoCreateEditDTO lostRelicInfoCreateEditDTO);

    LostRelicInfo update(@MappingTarget LostRelicInfo lostRelicInfo, LostRelicInfoCreateEditDTO lostRelicInfoCreateEditDTO);
}
