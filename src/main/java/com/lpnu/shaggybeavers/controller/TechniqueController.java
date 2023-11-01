package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.service.TechniqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/techniques")
@RequiredArgsConstructor
public class TechniqueController {

    private final TechniqueService techniqueService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getTechniqueById(@PathVariable Long id) {
        return ResponseEntity.ok(techniqueService.findById(id));
    }

}
