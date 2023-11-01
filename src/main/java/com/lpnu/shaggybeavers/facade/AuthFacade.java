package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.JwtDTO;
import com.lpnu.shaggybeavers.dto.auth.AuthenticationDTO;
import com.lpnu.shaggybeavers.dto.auth.RegistrationDTO;
import com.lpnu.shaggybeavers.factory.AuthFactory;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final AuthFactory authFactory;

    private final AuthService authService ;

    public void registration (RegistrationDTO request) {
        authService.registration(authFactory.RegistrationDtoToUser(request));
    }

    public JwtDTO authenticate (AuthenticationDTO request) {
        return authService.authenticate(request);
    }

}
