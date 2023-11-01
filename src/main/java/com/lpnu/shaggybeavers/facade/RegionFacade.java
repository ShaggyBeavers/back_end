package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.RegionFactory;
import com.lpnu.shaggybeavers.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegionFacade {

    private final RegionFactory regionFactory;

    private final RegionService regionService;

}
