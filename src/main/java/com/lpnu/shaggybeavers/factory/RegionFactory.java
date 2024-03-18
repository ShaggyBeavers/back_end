package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.RegionCreateDTO;
import com.lpnu.shaggybeavers.dto.RegionDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegionFactory {

    private final EntityMapper entityMapper;

    public Region toRegion(RegionCreateDTO regionCreateDTO) {
        return entityMapper.toRegion(regionCreateDTO);
    }

    public RegionDTO toRegionDTO(Region region) {
        return entityMapper.toRegionDTO(region);
    }

    public List<RegionDTO> toRegionDTOs(List<Region> regions) {
        return entityMapper.toRegionDTOs(regions);
    }

}
