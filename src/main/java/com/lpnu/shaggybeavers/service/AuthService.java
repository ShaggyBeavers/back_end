package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.dto.JwtDTO;
import com.lpnu.shaggybeavers.dto.LoginDTO;

public interface AuthService {

    JwtDTO login(LoginDTO loginDTO);
}
