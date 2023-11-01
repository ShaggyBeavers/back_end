package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.dto.auth.AuthenticationDTO;
import com.lpnu.shaggybeavers.dto.auth.RegistrationDTO;
import com.lpnu.shaggybeavers.dto.JwtDTO;
import com.lpnu.shaggybeavers.model.User;

public interface AuthService {

    void registration (User user);

    JwtDTO authenticate(AuthenticationDTO request);

}
