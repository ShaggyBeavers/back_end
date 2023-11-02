package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.facade.RelicCategoryFacade;
import com.lpnu.shaggybeavers.service.RelicCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/relic-categories")
@RequiredArgsConstructor
public class RelicCategoryController {

    private final RelicCategoryFacade relicCategoryFacade;

}