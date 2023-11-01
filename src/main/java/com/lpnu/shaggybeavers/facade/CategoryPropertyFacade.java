package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.CategoryPropertyFactory;
import com.lpnu.shaggybeavers.service.CategoryPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryPropertyFacade {

    private final CategoryPropertyFactory categoryPropertyFactory;

    private final CategoryPropertyService categoryPropertyService;

}
