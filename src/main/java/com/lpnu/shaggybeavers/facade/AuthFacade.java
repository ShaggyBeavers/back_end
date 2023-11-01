package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.AuthFactory;
import com.lpnu.shaggybeavers.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final AuthFactory authFactory;

    private final AuthService authService ;

}
