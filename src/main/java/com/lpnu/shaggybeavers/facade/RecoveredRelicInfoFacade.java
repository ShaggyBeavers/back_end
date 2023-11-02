package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.RecoveredRelicInfoFactory;
import com.lpnu.shaggybeavers.service.RecoveredRelicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecoveredRelicInfoFacade {

    private final RecoveredRelicInfoFactory recoveredRelicInfoFactory;

    private final RecoveredRelicInfoService recoveredRelicInfoService;

}
