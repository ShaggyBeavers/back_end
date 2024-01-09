package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.LostRelicInfoCreateEditDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.LostRelicInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LostRelicInfoFactory {

    private final EntityMapper entityMapper;

    public LostRelicInfo toLostRelicInfo(LostRelicInfoCreateEditDTO lostRelicInfoCreateEditDTO) {
        return entityMapper.toLostRelicInfo(lostRelicInfoCreateEditDTO);
    }
}
