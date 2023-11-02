package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.JwtDTO;
import com.lpnu.shaggybeavers.dto.auth.AuthenticationDTO;
import com.lpnu.shaggybeavers.dto.auth.RegistrationDTO;
import com.lpnu.shaggybeavers.factory.AuthFactory;
import com.lpnu.shaggybeavers.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final AuthFactory authFactory;

    private final AuthService authService ;

    @Transactional
    public void registration (RegistrationDTO request) {
        authService.registration(authFactory.toUser(request));
    }

    public JwtDTO authenticate (AuthenticationDTO request) {
         return authFactory.toJwtDTO(authService.authenticate(request));
    }

}
