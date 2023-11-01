package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.auth.AuthenticationDTO;
import com.lpnu.shaggybeavers.dto.JwtDTO;
import com.lpnu.shaggybeavers.facade.AuthFacade;
import com.lpnu.shaggybeavers.service.AuthService;
import com.lpnu.shaggybeavers.dto.auth.RegistrationDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthFacade authFacade;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> register(@Valid @RequestBody RegistrationDTO request) {
        authFacade.registration(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtDTO> authenticate(@RequestBody AuthenticationDTO request) {
        return ResponseEntity.ok((authFacade.authenticate(request)));
    }

}
