package com.lpnu.shaggybeavers.auth;

public interface AuthService {

    public AuthResponse register(RegisterRequest request);

    public AuthResponse authenticate(AuthRequest request);

}
