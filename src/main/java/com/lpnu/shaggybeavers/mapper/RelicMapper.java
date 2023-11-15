package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.RelicDTO;
import com.lpnu.shaggybeavers.model.Relic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RelicMapper {

    // Relic data
//    @Mapping(source = "relic.name", target = "name")
//    @Mapping(source = "relic.creationDate", target = "creationDate")
    @Mapping(source = "relic.creationPlace.name", target = "creationPlaceName")
//    @Mapping(source = "relic.picture", target = "picture")
    @Mapping(source = "relic.museum.name", target = "museumName")
    @Mapping(source = "relic.region.name", target = "regionName")
//    @Mapping(source = "relic.quantity", target = "quantity")
//    @Mapping(source = "relic.collection", target = "collection")
//    @Mapping(source = "relic.comment", target = "comment")
//    @Mapping(source = "relic.status", target = "status")

    // RelicProperty data ???
//    @Mapping(source = "relic.relicProperties", target = "relicPropertyDTOs")

    // RelicInfo data
//    @Mapping(source = "relic.relicInfo.technique.name", target = "relicInfoDTO.techniqueName")
//    @Mapping(source = "relic.relicInfo.historicalPeriod.name", target = "relicInfoDTO.historicalPeriodName")
//    @Mapping(source = "relic.relicInfo.dimensions", target = "relicInfoDTO.dimensions")
//    @Mapping(source = "relic.relicInfo.marks", target = "relicInfoDTO.marks")
//    @Mapping(source = "relic.relicInfo.labels", target = "relicInfoDTO.labels")
//    @Mapping(source = "relic.relicInfo.signatures", target = "relicInfoDTO.signatures")
//    @Mapping(source = "relic.relicInfo.restoration", target = "relicInfoDTO.restoration")
//    @Mapping(source = "relic.relicInfo.annotation", target = "relicInfoDTO.annotation")
    @Mapping(source = "relic.relicInfo", target = "relicInfoDTO")

    // RecoveredRelicInfo data
//    @Mapping(source = "relic.relicInfo.recoveredRelicInfo.locationSource", target = "recoveredRelicInfoDTO.locationSource")
//    @Mapping(source = "relic.relicInfo.recoveredRelicInfo.returnProcess", target = "recoveredRelicInfoDTO.returnProcess")
//    @Mapping(source = "relic.relicInfo.recoveredRelicInfo.returnDate", target = "recoveredRelicInfoDTO.returnDate")
//    @Mapping(source = "relic.relicInfo.recoveredRelicInfo.courtDecision", target = "recoveredRelicInfoDTO.courtDecision")
    @Mapping(source = "relic.relicInfo.recoveredRelicInfo", target = "recoveredRelicInfoDTO")

    // LostRelicInfo data
//    @Mapping(source = "relic.relicInfo.lostRelicInfo.lossWay", target = "lostRelicInfoDTO.lossWay")
//    @Mapping(source = "relic.relicInfo.lostRelicInfo.lossTime", target = "lostRelicInfoDTO.lossTime")
//    @Mapping(source = "relic.relicInfo.lostRelicInfo.museumName", target = "lostRelicInfoDTO.museumName")
//    @Mapping(source = "relic.relicInfo.lostRelicInfo.probableLocation", target = "lostRelicInfoDTO.probableLocation")
    @Mapping(source = "relic.relicInfo.lostRelicInfo", target = "lostRelicInfoDTO")

    RelicDTO toRelicDTO(Relic relic);

}
