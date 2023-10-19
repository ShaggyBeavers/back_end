package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.service.RelicCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/relic_categories")
@RequiredArgsConstructor
public class RelicCategoryController {

    private RelicCategoryService relicCategoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getRelicCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(relicCategoryService.findById(id));
    }
}
