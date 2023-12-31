package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.facade.RoleFacade;
import com.lpnu.shaggybeavers.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleFacade roleFacade;

}
