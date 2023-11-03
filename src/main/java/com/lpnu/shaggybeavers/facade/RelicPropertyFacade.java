package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.RelicPropertyFactory;
import com.lpnu.shaggybeavers.service.RelicPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RelicPropertyFacade {

    private final RelicPropertyFactory relicPropertyFactory;

    private final RelicPropertyService relicPropertyService;
}
