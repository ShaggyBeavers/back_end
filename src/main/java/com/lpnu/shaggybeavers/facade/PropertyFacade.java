package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.PropertyCreateDTO;
import com.lpnu.shaggybeavers.dto.PropertyDTO;
import com.lpnu.shaggybeavers.factory.PropertyFactory;
import com.lpnu.shaggybeavers.model.Property;
import com.lpnu.shaggybeavers.service.PropertyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PropertyFacade {

    private final PropertyFactory propertyFactory;

    private final PropertyService propertyService;

    @Transactional
    public Property findById(Long propertyId) {
        return propertyService.findById(propertyId);
    }

    @Transactional
    public void createProperty(PropertyCreateDTO propertyCreateDTO) {
        propertyService.save(propertyFactory.toProperty(propertyCreateDTO));
    }

    @Transactional
    public PropertyDTO getById(Long propertyId) {
        return propertyFactory.toPropertyDTO(propertyService.findById(propertyId));
    }

    @Transactional
    public List<PropertyDTO> getAll() {
        return propertyFactory.toPropertyDTOs(propertyService.findAll());
    }

}
