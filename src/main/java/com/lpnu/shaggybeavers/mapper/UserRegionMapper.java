package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.RegionDTO;
import com.lpnu.shaggybeavers.model.UserRegion;
import org.mapstruct.Mapping;

public interface UserRegionMapper {

    @Mapping(source = "region.id", target = "id")
    @Mapping(source = "region.name", target = "name")
    RegionDTO toRegionDTO(UserRegion userRegion);

}
