package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.RegionCreateDTO;
import com.lpnu.shaggybeavers.model.Region;

public interface RegionMapper {

    Region toRegion(RegionCreateDTO regionCreateDTO);

}
