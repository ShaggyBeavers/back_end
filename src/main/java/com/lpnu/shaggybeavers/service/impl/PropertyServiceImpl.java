package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.exception.AssociationException;
import com.lpnu.shaggybeavers.exception.DuplicateException;
import com.lpnu.shaggybeavers.model.Property;
import com.lpnu.shaggybeavers.repository.PropertyRepository;
import com.lpnu.shaggybeavers.service.PropertyService;
import com.lpnu.shaggybeavers.service.RelicPropertyService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl extends CRUDServiceImpl<Property, Long> implements PropertyService {

    private final RelicPropertyService relicPropertyService;

    private final PropertyRepository propertyRepository;

    @Lazy
    public PropertyServiceImpl(RelicPropertyService relicPropertyService, PropertyRepository propertyRepository) {
        this.relicPropertyService = relicPropertyService;
        this.propertyRepository = propertyRepository;
    }

    @Override
    protected JpaRepository<Property, Long> getRepository() {
        return this.propertyRepository;
    }

    @Override
    @Transactional
    public Property save(Property property) {
        if (propertyRepository.findByProperty(property.getProperty()) != null)  {
            throw new DuplicateException("Property already exists");
        }
        return propertyRepository.save(property);
    }

    @Override
    @Transactional
    public Property deleteById(Long propertyId) {
        Property property = findById(propertyId);

        if (relicPropertyService.existsByPropertyId(propertyId)) {
            throw new AssociationException("The property is associated with a relic");
        }

        propertyRepository.delete(property);
        return property;
    }

}
