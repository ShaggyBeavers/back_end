package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.RelicFactory;
import com.lpnu.shaggybeavers.service.RelicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RelicFacade {

    private final RelicFactory relicFactory;

    private final RelicService relicService;

}
