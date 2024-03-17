package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.RegionCreateDTO;
import com.lpnu.shaggybeavers.dto.RegionDTO;
import com.lpnu.shaggybeavers.model.Region;

import java.util.List;

public interface RegionMapper {

    Region toRegion(RegionCreateDTO regionCreateDTO);

    RegionDTO toRegionDTO(Region region);

    List<RegionDTO> toRegionDTOs(List<Region> regions);

}
