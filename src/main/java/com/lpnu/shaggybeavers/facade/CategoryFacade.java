package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.CategoryFactory;
import com.lpnu.shaggybeavers.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryFacade {

    private final CategoryFactory categoryFactory;

    private final CategoryService categoryService;

}
