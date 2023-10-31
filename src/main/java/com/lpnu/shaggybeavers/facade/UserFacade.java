package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.UserFactory;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserFactory userFactory;

    private final UserService userService;

    public User findById(Long id) {
        return userService.findById(id);
    }
}
