package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.dto.auth.AuthenticationDTO;
import com.lpnu.shaggybeavers.dto.auth.RegistrationDTO;
import com.lpnu.shaggybeavers.dto.JwtDTO;

public interface AuthService {

    void registration (RegistrationDTO request);

    JwtDTO authenticate(AuthenticationDTO request);

}
