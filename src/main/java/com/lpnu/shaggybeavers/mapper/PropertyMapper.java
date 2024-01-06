package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.PropertyCreateDTO;
import com.lpnu.shaggybeavers.model.Property;

public interface PropertyMapper {

    Property toProperty(PropertyCreateDTO propertyCreateDTO);

}
