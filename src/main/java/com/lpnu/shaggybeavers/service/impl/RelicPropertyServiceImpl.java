package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.RelicProperty;
import com.lpnu.shaggybeavers.repository.RelicPropertyRepository;
import com.lpnu.shaggybeavers.service.PropertyService;
import com.lpnu.shaggybeavers.service.RelicPropertyService;
import com.lpnu.shaggybeavers.service.RelicService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RelicPropertyServiceImpl extends CRUDServiceImpl<RelicProperty, Long> implements RelicPropertyService {

    private final RelicPropertyRepository relicPropertyRepository;

    private final PropertyService propertyService;

    private final RelicService relicService;

    @Lazy
    public RelicPropertyServiceImpl(RelicPropertyRepository relicPropertyRepository, PropertyService propertyService, RelicService relicService) {
        this.relicPropertyRepository = relicPropertyRepository;
        this.propertyService = propertyService;
        this.relicService = relicService;
    }

    @Override
    protected JpaRepository<RelicProperty, Long> getRepository() {
        return this.relicPropertyRepository;
    }

    @Override
    @Transactional
    public void create(Long relicId, Long propertyId, String value) {
        RelicProperty relicProperty = new RelicProperty();
        relicProperty.setProperty(propertyService.findById(propertyId));
        relicProperty.setRelic(relicService.findById(relicId));
        relicProperty.setValue(value);
        relicPropertyRepository.save(relicProperty);
    }

    @Override
    @Transactional
    public boolean existsByPropertyId(Long propertyId) {
        return relicPropertyRepository.existsByPropertyId(propertyId);
    }
}
