package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.ResetToken;
import com.lpnu.shaggybeavers.model.User;

public interface ResetTokenService extends CRUDService<ResetToken, Long> {

    ResetToken findResetTokenByUserId(Long userId);

    boolean isResetTokenExpired(ResetToken resetToken);

    void deleteResetToken(ResetToken resetToken);

    ResetToken generateResetToken(User user);

    void validateResetToken(String token);

    ResetToken findByToken(String token);

}
