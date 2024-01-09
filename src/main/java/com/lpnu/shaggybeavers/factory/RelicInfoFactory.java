package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.RelicInfoCreateEditDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.RelicInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RelicInfoFactory {

    private final EntityMapper entityMapper;

    public RelicInfo toRelicInfo(RelicInfoCreateEditDTO relicInfoCreateEditDTO) {
        return entityMapper.toRelicInfo(relicInfoCreateEditDTO);
    }
}
