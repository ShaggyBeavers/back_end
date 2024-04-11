package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.exception.AssociationException;
import com.lpnu.shaggybeavers.model.Region;
import com.lpnu.shaggybeavers.repository.RegionRepository;
import com.lpnu.shaggybeavers.service.RegionService;
import com.lpnu.shaggybeavers.service.RelicService;
import com.lpnu.shaggybeavers.service.ReportService;
import com.lpnu.shaggybeavers.service.UserRegionService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl extends CRUDServiceImpl<Region,Long> implements RegionService {

    private final UserRegionService userRegionService;

    private final ReportService reportService;

    private final RelicService relicService;

    private final RegionRepository repository;

    @Lazy
    public RegionServiceImpl(UserRegionService userRegionService, ReportService reportService, RelicService relicService, RegionRepository repository) {
        this.userRegionService = userRegionService;
        this.reportService = reportService;
        this.relicService = relicService;
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Region, Long> getRepository () {
        return this.repository;
    }

    @Override
    @Transactional
    public Region deleteById(Long regionId) {
        Region region = findById(regionId);

        if (userRegionService.existsByRegionId(regionId)) {
            throw new AssociationException("The region is associated with a user");
        }
        if (reportService.existsByRegionId(regionId)) {
            throw new AssociationException("The region is associated with a report");
        }
        if (relicService.existsByRegionId(regionId) || relicService.existsByCreationPlaceId(regionId)) {
            throw new AssociationException("The region is associated with a relic");
        }

        repository.delete(region);
        return region;
    }
}
