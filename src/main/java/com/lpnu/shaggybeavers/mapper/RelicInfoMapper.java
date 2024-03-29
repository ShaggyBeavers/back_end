package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.RelicInfoCreateEditDTO;
import com.lpnu.shaggybeavers.dto.RelicInfoDTO;
import com.lpnu.shaggybeavers.model.RelicInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface RelicInfoMapper {

    @Mapping(source = "technique.name", target = "techniqueName")
    @Mapping(source = "historicalPeriod.name", target = "historicalPeriodName")
    RelicInfoDTO toRelicInfoDTO(RelicInfo relicInfo);

    RelicInfo toRelicInfo(RelicInfoCreateEditDTO relicInfoCreateEditDTO);

    RelicInfo update(@MappingTarget RelicInfo relicInfo, RelicInfoCreateEditDTO relicInfoCreateEditDTO);
}
