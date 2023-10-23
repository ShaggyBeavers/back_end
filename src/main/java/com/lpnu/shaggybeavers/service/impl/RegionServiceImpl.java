package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.Region;
import com.lpnu.shaggybeavers.repository.RegionRepository;
import com.lpnu.shaggybeavers.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl extends CRUDServiceImpl<Region,Long> implements RegionService {

    private final RegionRepository repository;

    @Override
    protected JpaRepository<Region, Long> getRepository () {
        return this.repository;
    }
}
