package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.RegionCreateDTO;
import com.lpnu.shaggybeavers.facade.RegionFacade;
import com.lpnu.shaggybeavers.model.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/region")
@RequiredArgsConstructor
public class RegionController {

    private final RegionFacade regionFacade;

    @PostMapping("/create")
    public ResponseEntity<Void> createRegion(@RequestBody RegionCreateDTO regionCreateDTO){
        regionFacade.createRegion(regionCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{regionId}")
    public ResponseEntity<Region> getRegionById(@PathVariable Long regionId) {
        return new ResponseEntity<>(regionFacade.findById(regionId), HttpStatus.OK);
    }

}
