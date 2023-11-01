package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.facade.UserRegionFacade;
import com.lpnu.shaggybeavers.factory.UserRegionFactory;
import com.lpnu.shaggybeavers.service.UserRegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user_region")
@RequiredArgsConstructor
public class UserRegionController {

    private final UserRegionFacade userRegionFacade;

}
