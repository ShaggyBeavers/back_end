package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.LostRelicInfoFactory;
import com.lpnu.shaggybeavers.service.LostRelicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LostRelicInfoFacade {

    private final LostRelicInfoFactory lostRelicInfoFactory;

    private final LostRelicInfoService lostRelicInfoService;

}
