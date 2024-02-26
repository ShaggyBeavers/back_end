package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.RegionCreateDTO;
import com.lpnu.shaggybeavers.dto.TechniqueCreateDTO;
import com.lpnu.shaggybeavers.facade.RegionFacade;
import com.lpnu.shaggybeavers.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

}
