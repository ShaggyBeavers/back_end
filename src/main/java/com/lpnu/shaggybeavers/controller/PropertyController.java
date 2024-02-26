package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.PropertyCreateDTO;
import com.lpnu.shaggybeavers.facade.PropertyFacade;
import com.lpnu.shaggybeavers.model.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyFacade propertyFacade;

    @PostMapping("/create")
    public ResponseEntity<Void> createProperty(@RequestBody PropertyCreateDTO propertyCreateDTO){
        propertyFacade.createProperty(propertyCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long propertyId) {
        return new ResponseEntity<>(propertyFacade.findById(propertyId), HttpStatus.OK);
    }

}
