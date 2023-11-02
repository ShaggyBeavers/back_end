package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.UserRegionFactory;
import com.lpnu.shaggybeavers.service.UserRegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegionFacade {

    private final UserRegionFactory userRegionFactory;

    private final UserRegionService userRegionService;

}
