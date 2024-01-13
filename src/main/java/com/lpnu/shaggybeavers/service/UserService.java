package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.ResetToken;
import com.lpnu.shaggybeavers.model.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface UserService extends CRUDService<User,Long> {

    List<User> findAll(Specification<User> specification);

    User findByEmail(String email);

    ResetToken findResetTokenByUserId(Long userId);

    boolean isResetTokenExpired(ResetToken resetToken);

    void sendResetPasswordEmail(User user, String token);

    void deleteResetToken(ResetToken resetToken);

    ResetToken generateResetToken(User user);

    void validateResetToken(String token);

    void changePassword(String token, String password);

}
