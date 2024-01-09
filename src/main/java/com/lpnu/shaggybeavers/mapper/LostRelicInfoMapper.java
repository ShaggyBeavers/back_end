package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.LostRelicInfoCreateEditDTO;
import com.lpnu.shaggybeavers.model.LostRelicInfo;

public interface LostRelicInfoMapper {

    LostRelicInfo toLostRelicInfo(LostRelicInfoCreateEditDTO lostRelicInfoCreateEditDTO);
}
