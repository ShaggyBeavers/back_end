package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.TechniqueCreateDTO;
import com.lpnu.shaggybeavers.dto.TechniqueDTO;
import com.lpnu.shaggybeavers.facade.TechniqueFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/techniques")
@RequiredArgsConstructor
public class TechniqueController {

    private final TechniqueFacade techniqueFacade;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'REGIONAL_MODERATOR')")
    public ResponseEntity<Void> createTechnique(@RequestBody TechniqueCreateDTO techniqueCreateDTO){
        techniqueFacade.createTechnique(techniqueCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{techniqueId}")
    public ResponseEntity<TechniqueDTO> getTechniqueById(@PathVariable Long techniqueId) {
        return new ResponseEntity<>(techniqueFacade.getById(techniqueId), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<TechniqueDTO>> getTechniques() {
        return new ResponseEntity<>(techniqueFacade.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{techniqueId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'REGIONAL_MODERATOR', 'MODERATOR')")
    public ResponseEntity<Void> delete(@PathVariable Long techniqueId) {
        techniqueFacade.deleteById(techniqueId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
