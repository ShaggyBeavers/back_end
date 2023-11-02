package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.facade.RecoveredRelicInfoFacade;

import com.lpnu.shaggybeavers.service.RecoveredRelicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/recovered-relic-infos")
@RequiredArgsConstructor
public class RecoveredRelicInfoController {

    private final RecoveredRelicInfoFacade recoveredRelicInfoFacade;


}