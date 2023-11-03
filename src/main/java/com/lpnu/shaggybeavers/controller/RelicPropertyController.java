package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.facade.RelicPropertyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/relic-properties")
@RequiredArgsConstructor
public class RelicPropertyController {

    private final RelicPropertyFacade relicPropertyFacade;

}
