package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.ResetTokenFactory;
import com.lpnu.shaggybeavers.model.ResetToken;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.service.ResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResetTokenFacade {

    private final ResetTokenFactory resetTokenFactory;

    private final ResetTokenService resetTokenService;

    public ResetToken findByUserId(Long userId) {
        return resetTokenService.findByUserId(userId);
    }

    public boolean isResetTokenExpired(ResetToken resetToken) {
        return resetTokenService.isResetTokenExpired(resetToken);
    }

    public void deleteResetToken(ResetToken resetToken) {
        resetTokenService.deleteResetToken(resetToken);
    }

    public ResetToken generateResetToken(User user) {
        return resetTokenService.generateResetToken(user);
    }

    public void validateResetToken(String token) {
        resetTokenService.validateResetToken(token);
    }

    public ResetToken findByToken(String token) {
        return resetTokenService.findByToken(token);
    }

}
