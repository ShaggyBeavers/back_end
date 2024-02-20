package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.HistoricalPeriodCreateDTO;
import com.lpnu.shaggybeavers.facade.HistoricalPeriodFacade;

import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping(value = "/api/historical-periods")
@RequiredArgsConstructor
public class HistoricalPeriodController {

    private final HistoricalPeriodFacade historicalPeriodFacade;

    @PostMapping("/create")
    public ResponseEntity<Void> createHistoricalPeriod(@RequestBody HistoricalPeriodCreateDTO historicalPeriodCreateDTO){
        historicalPeriodFacade.createHistoricalPeriod(historicalPeriodCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}