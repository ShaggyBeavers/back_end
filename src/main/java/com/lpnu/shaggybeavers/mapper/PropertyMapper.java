package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.PropertyCreateDTO;
import com.lpnu.shaggybeavers.dto.PropertyDTO;
import com.lpnu.shaggybeavers.model.Property;

import java.util.List;

public interface PropertyMapper {

    Property toProperty(PropertyCreateDTO propertyCreateDTO);

    PropertyDTO toPropertyDTO(Property property);

    List<PropertyDTO> toPropertyDTOs(List<Property> properties);
}
