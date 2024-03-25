package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.PropertyCreateDTO;
import com.lpnu.shaggybeavers.dto.PropertyDTO;
import com.lpnu.shaggybeavers.model.Property;
import org.mapstruct.Mapping;

import java.util.List;

public interface PropertyMapper {
    @Mapping(source = "name", target = "property")
    Property toProperty(PropertyCreateDTO propertyCreateDTO);

    PropertyDTO toPropertyDTO(Property property);

    List<PropertyDTO> toPropertyDTOs(List<Property> properties);
}
