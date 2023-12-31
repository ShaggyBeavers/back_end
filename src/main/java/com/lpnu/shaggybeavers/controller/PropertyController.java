package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.facade.PropertyFacade;
import com.lpnu.shaggybeavers.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyFacade propertyFacade;

}
