package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.facade.HistoricalPeriodFacade;

import com.lpnu.shaggybeavers.service.HistoricalPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping(value = "/api/historical-periods")
@RequiredArgsConstructor
public class HistoricalPeriodController {

    private final HistoricalPeriodFacade historicalPeriodFacade;

}