package com.lpnu.shaggybeavers.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EntityMapper extends AuthMapper, CategoryMapper, RelicPropertyMapper,
        HistoricalPeriodMapper, LostRelicInfoMapper, MuseumMapper, PropertyMapper,
        RecoveredRelicInfoMapper, RegionMapper, RelicCategoryMapper, RelicInfoMapper,
        RelicMapper, ReportMapper, RoleMapper, TechniqueMapper, UserMapper, UserRegionMapper,
        StorageMapper, ReportCategoryMapper, UserCategoryMapper {
}
