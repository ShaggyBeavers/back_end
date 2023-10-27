package com.lpnu.shaggybeavers.auth;

import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.repository.CategoryRepository;
import com.lpnu.shaggybeavers.repository.UserRepository;
import com.lpnu.shaggybeavers.security.UserPrincipal;
import com.lpnu.shaggybeavers.security.jwt.JwtUtil;
import com.lpnu.shaggybeavers.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        var jwtToken = jwtUtil.generateToken(
                UserPrincipal.builder()
                        .user(user).
                        build());
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtUtil.generateToken(
                UserPrincipal.builder()
                        .user(user).
                        build());
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
