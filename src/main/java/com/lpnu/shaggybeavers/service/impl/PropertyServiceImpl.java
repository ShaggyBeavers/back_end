package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.exception.DuplicateException;
import com.lpnu.shaggybeavers.model.Property;
import com.lpnu.shaggybeavers.repository.PropertyRepository;
import com.lpnu.shaggybeavers.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl extends CRUDServiceImpl<Property, Long> implements PropertyService {

    private final PropertyRepository propertyRepository;

    @Override
    protected JpaRepository<Property, Long> getRepository() {
        return this.propertyRepository;
    }

    @Override
    public Property save(Property property) {
        if (propertyRepository.findByProperty(property.getProperty()) != null)  {
            throw new DuplicateException("Property already exists");
        }
        return propertyRepository.save(property);
      }
}
