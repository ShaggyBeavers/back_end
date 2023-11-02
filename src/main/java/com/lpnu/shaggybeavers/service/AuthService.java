package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.dto.auth.AuthenticationDTO;
import com.lpnu.shaggybeavers.model.User;

public interface AuthService {

    void registration (User user);

    String authenticate(AuthenticationDTO request);

}
