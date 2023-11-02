package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.TechiqueFactory;
import com.lpnu.shaggybeavers.service.TechniqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TechniqueFacade {

    private final TechiqueFactory techiqueFactory;

    private final TechniqueService techniqueService;

}
