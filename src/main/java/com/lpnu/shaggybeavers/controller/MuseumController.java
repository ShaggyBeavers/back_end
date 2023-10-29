package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.service.MuseumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/museums")
@RequiredArgsConstructor
public class MuseumController {

    private final MuseumService museumService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getMuseumById(@PathVariable Long id) {
        return ResponseEntity.ok(museumService.findById(id));
    }

}
