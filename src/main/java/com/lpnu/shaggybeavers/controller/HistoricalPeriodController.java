package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.HistoricalPeriodCreateDTO;
import com.lpnu.shaggybeavers.dto.HistoricalPeriodDTO;
import com.lpnu.shaggybeavers.facade.HistoricalPeriodFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping(value = "/api/historical-periods")
@RequiredArgsConstructor
public class HistoricalPeriodController {

    private final HistoricalPeriodFacade historicalPeriodFacade;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'REGIONAL_MODERATOR')")
    public ResponseEntity<Void> createHistoricalPeriod(@RequestBody HistoricalPeriodCreateDTO historicalPeriodCreateDTO){
        historicalPeriodFacade.createHistoricalPeriod(historicalPeriodCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{historicalPeriodId}")
    public ResponseEntity<HistoricalPeriodDTO> getHistoricalPeriodById(@PathVariable Long historicalPeriodId) {
        return new ResponseEntity<>(historicalPeriodFacade.findById(historicalPeriodId), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<HistoricalPeriodDTO>> getHistoricalPeriods() {
        return new ResponseEntity<>(historicalPeriodFacade.findAll(), HttpStatus.OK);
    }

}