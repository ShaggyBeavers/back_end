package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.facade.UserFacade;
import com.lpnu.shaggybeavers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserContoller {

    private final UserFacade userFacade;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok( userFacade.findById(id));
    }

}
