package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.MuseumCreateDTO;
import com.lpnu.shaggybeavers.dto.MuseumDTO;
import com.lpnu.shaggybeavers.facade.MuseumFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/museums")
@RequiredArgsConstructor
public class MuseumController {

    private final MuseumFacade museumFacade;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'REGIONAL_MODERATOR')")
    public ResponseEntity<Void> createMuseum(@RequestBody MuseumCreateDTO museumCreateDTO) {
        museumFacade.createMuseum(museumCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{museumId}")
    public ResponseEntity<MuseumDTO> getMuseumById(@PathVariable Long museumId) {
        return new ResponseEntity<>(museumFacade.getById(museumId), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<MuseumDTO>> getMuseums() {
        return new ResponseEntity<>(museumFacade.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{museumId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'REGIONAL_MODERATOR', 'MODERATOR')")
    public ResponseEntity<Void> delete(@PathVariable Long museumId) {
        museumFacade.deleteById(museumId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
