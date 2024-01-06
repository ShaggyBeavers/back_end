package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.TechniqueCreateDTO;
import com.lpnu.shaggybeavers.facade.TechniqueFacade;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/techniques")
@RequiredArgsConstructor
public class TechniqueController {

    private final TechniqueFacade techniqueFacade;

    @PostMapping("/create")
    public void createTechnique(@RequestBody TechniqueCreateDTO techniqueCreateDTO){
        techniqueFacade.createTechnique(techniqueCreateDTO);
    }

}
