package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.service.RelicExampleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/relic")
@RequiredArgsConstructor
public class RelicController {

    private RelicExampleService relicExampleService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getRelicById(@PathVariable Long id) {
        return ResponseEntity.ok( relicExampleService.findById(id));
        /*You mustn't return entities , you need to transform that entity to certain dto using transformer pattern or some architecture layer */
    }

}
