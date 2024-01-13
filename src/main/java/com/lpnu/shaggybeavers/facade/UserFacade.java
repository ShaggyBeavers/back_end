package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.ResetPasswordDTO;
import com.lpnu.shaggybeavers.dto.UserDTO;
import com.lpnu.shaggybeavers.dto.UserProfileDTO;
import com.lpnu.shaggybeavers.exception.NotEqualObjectsException;
import com.lpnu.shaggybeavers.factory.UserFactory;
import com.lpnu.shaggybeavers.filter.UserFilter;
import com.lpnu.shaggybeavers.model.ResetToken;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.service.UserService;
import com.lpnu.shaggybeavers.specification.UserSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

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

    @Transactional
    public void requestResetPassword(String email) {
        User user = userService.findByEmail(email);
        ResetToken currentResetToken = userService.findResetTokenByUserId(user.getId());

        if (currentResetToken != null && !userService.isResetTokenExpired(currentResetToken)) {
            userService.sendResetPasswordEmail(user, currentResetToken.getToken());
            return;
        } else if (currentResetToken != null && userService.isResetTokenExpired(currentResetToken)) {
            userService.deleteResetToken(currentResetToken);
        }

        ResetToken resetToken = userService.generateResetToken(user);
        userService.sendResetPasswordEmail(user, resetToken.getToken());
    }

    @Transactional
    public void resetPassword(ResetPasswordDTO dto) {
        userService.validateResetToken(dto.getToken());

        if (!Objects.equals(dto.getPassword(), dto.getPasswordConfirmation())) {
            throw new NotEqualObjectsException("Password and password confirmation aren't equal");
        }

        userService.changePassword(dto.getToken(), dto.getPassword());
    }

}
