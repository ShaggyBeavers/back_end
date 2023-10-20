package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

}
