package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.UserProfileDTO;
import com.lpnu.shaggybeavers.factory.UserFactory;
import com.lpnu.shaggybeavers.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserFactory userFactory;

    private final UserService userService;

    private final StorageFacade storageFacade;

    @Transactional
    public UserProfileDTO getProfile(Long userId) {
        return userFactory.toUserDTO(userService.findById(userId));
    }


}
