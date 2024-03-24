package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.RegionCreateDTO;
import com.lpnu.shaggybeavers.dto.RegionDTO;
import com.lpnu.shaggybeavers.factory.RegionFactory;
import com.lpnu.shaggybeavers.model.Region;
import com.lpnu.shaggybeavers.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegionFacade {

    private final RegionFactory regionFactory;

    private final RegionService regionService;

    @Transactional
    public void createRegion(RegionCreateDTO regionCreateDTO) {
        regionService.save(regionFactory.toRegion(regionCreateDTO));
    }

    @Transactional
    public Region findById(Long regionId) {
        return regionService.findById(regionId);
    }

    @Transactional
    public RegionDTO getById(Long regionId) {
        return regionFactory.toRegionDTO(regionService.findById(regionId));
    }

    @Transactional
    public List<RegionDTO> getAll() {
        return regionFactory.toRegionDTOs(regionService.findAll());
    }

    @Transactional
    public void deleteById(Long regionId) {
        regionService.deleteById(regionId);
    }
}
