package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.PropertyCreateDTO;
import com.lpnu.shaggybeavers.factory.PropertyFactory;
import com.lpnu.shaggybeavers.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PropertyFacade {

    private final PropertyFactory propertyFactory;

    private final PropertyService propertyService;

    public void createProperty(PropertyCreateDTO propertyCreateDTO) {
        propertyService.save(propertyFactory.toProperty(propertyCreateDTO));
    }
}
