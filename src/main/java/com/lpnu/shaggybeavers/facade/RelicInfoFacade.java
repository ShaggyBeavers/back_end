package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.RelicInfoFactory;
import com.lpnu.shaggybeavers.service.RelicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RelicInfoFacade {

    private final RelicInfoFactory relicInfoFactory;

    private final RelicInfoService relicInfoService;

}
