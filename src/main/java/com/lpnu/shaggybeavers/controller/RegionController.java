package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.RegionCreateDTO;
import com.lpnu.shaggybeavers.dto.RegionDTO;
import com.lpnu.shaggybeavers.facade.RegionFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/region")
@RequiredArgsConstructor
public class RegionController {

    private final RegionFacade regionFacade;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'REGIONAL_MODERATOR')")
    public ResponseEntity<Void> createRegion(@RequestBody RegionCreateDTO regionCreateDTO){
        regionFacade.createRegion(regionCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{regionId}")
    public ResponseEntity<RegionDTO> getRegionById(@PathVariable Long regionId) {
        return new ResponseEntity<>(regionFacade.findById(regionId), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<RegionDTO>> getRegions() {
        return new ResponseEntity<>(regionFacade.findAll(), HttpStatus.OK);
    }

}
