package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.JwtDTO;
import com.lpnu.shaggybeavers.dto.LoginDTO;
import com.lpnu.shaggybeavers.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(
            @RequestBody LoginDTO loginDTO
    ) {
        return ResponseEntity.ok(authService.login(loginDTO));
    }

}
