package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.RelicPropertyCreateDTO;
import com.lpnu.shaggybeavers.dto.RelicPropertyDTO;
import com.lpnu.shaggybeavers.model.RelicProperty;
import org.mapstruct.Mapping;

public interface RelicPropertyMapper {

    @Mapping(source = "property.property", target = "name")
    RelicPropertyDTO toRelicPropertyDTO(RelicProperty relicProperty);

    RelicProperty toRelicProperty(RelicPropertyCreateDTO relicPropertyCreateDTO);
}
