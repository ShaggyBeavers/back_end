package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.RegionCreateDTO;
import com.lpnu.shaggybeavers.factory.RegionFactory;
import com.lpnu.shaggybeavers.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RegionFacade {

    private final RegionFactory regionFactory;

    private final RegionService regionService;

    @Transactional
    public void createRegion(RegionCreateDTO regionCreateDTO) {
        regionService.save(regionFactory.toRegion(regionCreateDTO));
    }
}
