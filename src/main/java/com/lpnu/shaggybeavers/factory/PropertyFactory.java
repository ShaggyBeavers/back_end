package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.PropertyCreateDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PropertyFactory {

    private final EntityMapper entityMapper;

    public Property toProperty(PropertyCreateDTO propertyCreateDTO) {
        return entityMapper.toProperty(propertyCreateDTO);
    }
}
