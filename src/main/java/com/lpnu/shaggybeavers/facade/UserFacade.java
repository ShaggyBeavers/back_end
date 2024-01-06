package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.UserDTO;
import com.lpnu.shaggybeavers.dto.UserProfileDTO;
import com.lpnu.shaggybeavers.factory.UserFactory;
import com.lpnu.shaggybeavers.filter.UserFilter;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.service.UserService;
import com.lpnu.shaggybeavers.specification.UserSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserFactory userFactory;

    private final UserService userService;

    private final StorageFacade storageFacade;

    @Transactional
    public UserProfileDTO getProfile(Long userId) {
        return userFactory.toUserProfileDTO(userService.findById(userId));
    }

    @Transactional
    public List<UserDTO> getUsersByFilter(UserFilter filter) {
        Specification<User> specification = new UserSpecification(filter);
        List<User> users = userService.findAll(specification);
        return userFactory.toUserDTOList(users);
    }

    @Transactional
    public void deleteUserById(Long userId) {
        userService.deleteById(userId);
    }

    @Transactional
    public User getUserById(Long userId) {
        return userService.findById(userId);
    }

}
