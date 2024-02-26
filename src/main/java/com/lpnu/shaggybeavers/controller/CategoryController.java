package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.CategoryCreateDTO;
import com.lpnu.shaggybeavers.dto.CategoryDTO;
import com.lpnu.shaggybeavers.facade.CategoryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryFacade categoryFacade;

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> readCategories() {
        return new ResponseEntity<>(categoryFacade.readCategories(), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'REGIONAL_MODERATOR')")
    public ResponseEntity<Void> createCategory(@RequestBody CategoryCreateDTO categoryCreateDTO){
        categoryFacade.createCategory(categoryCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
