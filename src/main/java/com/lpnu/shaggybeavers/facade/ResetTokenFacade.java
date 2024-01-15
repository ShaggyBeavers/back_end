package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.ResetTokenFactory;
import com.lpnu.shaggybeavers.model.ResetToken;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.service.ResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ResetTokenFacade {

    private final ResetTokenFactory resetTokenFactory;

    private final ResetTokenService resetTokenService;

    @Transactional
    public ResetToken findByUserId(Long userId) {
        return resetTokenService.findByUserId(userId);
    }

    @Transactional
    public boolean isResetTokenExpired(ResetToken resetToken) {
        return resetTokenService.isResetTokenExpired(resetToken);
    }

    @Transactional
    public void deleteResetToken(ResetToken resetToken) {
        resetTokenService.deleteResetToken(resetToken);
    }

    @Transactional
    public ResetToken generateResetToken(User user) {
        return resetTokenService.generateResetToken(user);
    }

    @Transactional
    public void validateResetToken(String token) {
        resetTokenService.validateResetToken(token);
    }

    @Transactional
    public ResetToken findByToken(String token) {
        return resetTokenService.findByToken(token);
    }

}
