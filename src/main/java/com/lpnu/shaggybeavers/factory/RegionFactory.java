package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.RegionCreateDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegionFactory {

    private final EntityMapper entityMapper;

    public Region toRegion(RegionCreateDTO regionCreateDTO) {
        return entityMapper.toRegion(regionCreateDTO);
    }
}
