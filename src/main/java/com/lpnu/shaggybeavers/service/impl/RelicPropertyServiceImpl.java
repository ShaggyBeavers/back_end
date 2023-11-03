package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.RelicProperty;
import com.lpnu.shaggybeavers.repository.RelicPropertyRepository;
import com.lpnu.shaggybeavers.service.RelicPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelicPropertyServiceImpl extends CRUDServiceImpl<RelicProperty, Long> implements RelicPropertyService {

    private final RelicPropertyRepository relicPropertyRepository;

    @Override
    protected JpaRepository<RelicProperty, Long> getRepository() {
        return this.relicPropertyRepository;
    }
}
