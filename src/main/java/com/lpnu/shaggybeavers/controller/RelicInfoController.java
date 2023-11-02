package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.facade.RelicInfoFacade;

import com.lpnu.shaggybeavers.service.RelicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/relic-infos")
@RequiredArgsConstructor
public class RelicInfoController {

    private final RelicInfoFacade relicInfoFacade;

}