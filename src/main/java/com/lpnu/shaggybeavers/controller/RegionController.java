package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/region")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getRegionById(@PathVariable Long id) {
        return ResponseEntity.ok( regionService.findById(id));
    }

}