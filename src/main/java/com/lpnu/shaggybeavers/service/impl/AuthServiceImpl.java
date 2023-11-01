package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.dto.auth.AuthenticationDTO;
import com.lpnu.shaggybeavers.dto.auth.RegistrationDTO;
import com.lpnu.shaggybeavers.dto.JwtDTO;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.security.UserPrincipal;
import com.lpnu.shaggybeavers.security.jwt.JwtUtil;
import com.lpnu.shaggybeavers.service.AuthService;
import com.lpnu.shaggybeavers.service.RoleService;
import com.lpnu.shaggybeavers.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final RoleService roleService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void registration (User user) {
        user.setRole(roleService.findByName("USER"));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.save(user);
    }

    @Override
    public JwtDTO authenticate (AuthenticationDTO request) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        var userPrincipal = (UserPrincipal) authentication.getPrincipal();
        var accessToken = jwtUtil.generateToken(userPrincipal);
        var tokenDto = new JwtDTO();
        tokenDto.setAccessToken(accessToken);
        return tokenDto;
    }
}
