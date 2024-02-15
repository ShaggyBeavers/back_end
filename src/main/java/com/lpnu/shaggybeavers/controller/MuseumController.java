package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.MuseumCreateDTO;
import com.lpnu.shaggybeavers.facade.MuseumFacade;

import com.lpnu.shaggybeavers.service.MuseumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/museums")
@RequiredArgsConstructor
public class MuseumController {

    private final MuseumFacade museumFacade;

    @PostMapping("/create")
    public ResponseEntity<Void> createMuseum(@RequestBody MuseumCreateDTO museumCreateDTO) {
        museumFacade.createMuseum(museumCreateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
