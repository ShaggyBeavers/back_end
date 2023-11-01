package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.RelicCategoryFactory;
import com.lpnu.shaggybeavers.service.RelicCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RelicCategoryFacade {

    private final RelicCategoryFactory relicCategoryFactory;

    private final RelicCategoryService relicCategoryService;

}
