package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.exception.NotExistsObjectException;
import com.lpnu.shaggybeavers.exception.TokenException;
import com.lpnu.shaggybeavers.model.ResetToken;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.repository.ResetTokenRepository;
import com.lpnu.shaggybeavers.service.ResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResetTokenServiceImpl extends CRUDServiceImpl<ResetToken, Long> implements ResetTokenService {

    @Value("${RESET_TOKEN_EXPIRATION_IN_HOURS}")
    private int resetTokenExpirationInHours;

    private final ResetTokenRepository repository;

    @Override
    protected JpaRepository<ResetToken, Long> getRepository() {
        return repository;
    }

    @Override
    @Transactional
    public ResetToken findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    @Transactional
    public boolean isResetTokenExpired(ResetToken resetToken) {
        return resetToken.getExpirationDate().isBefore(LocalDateTime.now());
    }

    @Override
    @Transactional
    public void deleteResetToken(ResetToken resetToken) {
        repository.delete(resetToken);
    }

    @Override
    @Transactional
    public ResetToken generateResetToken(User user) {
        ResetToken resetToken = new ResetToken();
        resetToken.setToken(UUID.randomUUID().toString());
        resetToken.setUser(user);
        resetToken.setExpirationDate(LocalDateTime.now().plusHours(resetTokenExpirationInHours));
        return repository.save(resetToken);
    }

    @Override
    @Transactional
    public void validateResetToken(String token) {
        ResetToken resetToken = this.findByToken(token);
        if (this.isResetTokenExpired(resetToken)) {
            throw new TokenException("Token is expired");
        }
    }

    @Override
    @Transactional
    public ResetToken findByToken(String token) {
        return repository.findByToken(token)
                .orElseThrow(() -> new NotExistsObjectException("Reset token doesn't exist"));
    }

}
