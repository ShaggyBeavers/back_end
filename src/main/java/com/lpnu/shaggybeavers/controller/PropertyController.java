package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.PropertyCreateDTO;
import com.lpnu.shaggybeavers.dto.PropertyDTO;
import com.lpnu.shaggybeavers.facade.PropertyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyFacade propertyFacade;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'REGIONAL_MODERATOR')")
    public ResponseEntity<Void> createProperty(@RequestBody PropertyCreateDTO propertyCreateDTO){
        propertyFacade.createProperty(propertyCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long propertyId) {
        return new ResponseEntity<>(propertyFacade.findById(propertyId), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<PropertyDTO>> getProperties() {
        return new ResponseEntity<>(propertyFacade.findAll(), HttpStatus.OK);
    }

}
