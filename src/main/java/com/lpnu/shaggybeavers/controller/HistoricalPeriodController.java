package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.HistoricalPeriodCreateDTO;
import com.lpnu.shaggybeavers.facade.HistoricalPeriodFacade;
import com.lpnu.shaggybeavers.model.HistoricalPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{historicalPeriodId}")
    public ResponseEntity<HistoricalPeriod> getHistoricalPeriodById(@PathVariable Long historicalPeriodId) {
        return new ResponseEntity<>(historicalPeriodFacade.findById(historicalPeriodId), HttpStatus.OK);
    }

}