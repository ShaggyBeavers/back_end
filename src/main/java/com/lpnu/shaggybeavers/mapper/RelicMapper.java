package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.RelicCatalogDTO;
import com.lpnu.shaggybeavers.dto.RelicDTO;
import com.lpnu.shaggybeavers.dto.RelicCreateEditDTO;
import com.lpnu.shaggybeavers.model.Relic;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface RelicMapper {

    @Mapping(source = "creationPlace.name", target = "creationPlaceName")
    @Mapping(source = "museum.name", target = "museumName")
    @Mapping(source = "region.name", target = "regionName")
    @Mapping(source = "relicProperties", target = "relicPropertyDTOs")
    @Mapping(source = "relicInfo", target = "relicInfoDTO")
    @Mapping(source = "relicInfo.recoveredRelicInfo", target = "recoveredRelicInfoDTO")
    @Mapping(source = "relicInfo.lostRelicInfo", target = "lostRelicInfoDTO")
    RelicDTO toRelicDTO(Relic relic);

    RelicCatalogDTO toRelicCatalogDTO(Relic relic);

    List<RelicDTO> toRelicDTOList(List<Relic> relics);

    Relic toRelic(RelicCreateEditDTO relicCreateEditDTO);

    Relic update(@MappingTarget Relic relic, RelicCreateEditDTO relicCreateEditDTO);
}
