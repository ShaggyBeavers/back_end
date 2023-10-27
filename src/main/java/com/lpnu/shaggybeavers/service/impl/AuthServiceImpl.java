package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.dto.JwtDTO;
import com.lpnu.shaggybeavers.dto.LoginDTO;
import com.lpnu.shaggybeavers.security.jwt.JwtUtil;
import com.lpnu.shaggybeavers.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public JwtDTO login(LoginDTO loginDTO) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );
        var user = (UserDetails) authentication.getPrincipal();
        var jwtToken = jwtUtil.generateToken(user);
        return JwtDTO.builder()
                .accessToken(jwtToken)
                .build();
    }
}
