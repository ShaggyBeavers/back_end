package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.PropertyCreateDTO;
import com.lpnu.shaggybeavers.dto.PropertyDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PropertyFactory {

    private final EntityMapper entityMapper;

    public Property toProperty(PropertyCreateDTO propertyCreateDTO) {
        return entityMapper.toProperty(propertyCreateDTO);
    }

    public PropertyDTO toPropertyDTO(Property property) {
        return entityMapper.toPropertyDTO(property);
    }

    public List<PropertyDTO> toPropertyDTOs(List<Property> properties) {
        return entityMapper.toPropertyDTOs(properties);
    }
}
