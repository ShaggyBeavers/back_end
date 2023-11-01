package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.facade.CategoryPropertyFacade;
import com.lpnu.shaggybeavers.service.CategoryPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/category_properties")
@RequiredArgsConstructor
public class CategoryPropertyController {

    private final CategoryPropertyFacade categoryPropertyFacade;

}
