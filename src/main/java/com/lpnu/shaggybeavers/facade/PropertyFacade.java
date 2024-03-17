package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.PropertyCreateDTO;
import com.lpnu.shaggybeavers.dto.PropertyDTO;
import com.lpnu.shaggybeavers.factory.PropertyFactory;
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
    public PropertyDTO findById(Long propertyId) {
        return propertyFactory.toPropertyDTO(propertyService.findById(propertyId));
    }

    @Transactional
    public void createProperty(PropertyCreateDTO propertyCreateDTO) {
        propertyService.save(propertyFactory.toProperty(propertyCreateDTO));
    }

    @Transactional
    public List<PropertyDTO> findAll() {
        return propertyFactory.toPropertyDTOs(propertyService.findAll());
    }

}
