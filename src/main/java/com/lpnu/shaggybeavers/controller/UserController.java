package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.UserProfileDTO;
import com.lpnu.shaggybeavers.facade.UserFacade;
import com.lpnu.shaggybeavers.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/current-profile")
    public ResponseEntity<UserProfileDTO> getProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return new ResponseEntity<>(userFacade.getProfile(userPrincipal.getId()), HttpStatus.OK);
    }

}
