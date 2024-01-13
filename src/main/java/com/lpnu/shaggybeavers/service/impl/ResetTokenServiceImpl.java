package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.exception.NotExistsObjectException;
import com.lpnu.shaggybeavers.exception.TokenException;
import com.lpnu.shaggybeavers.model.ResetToken;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.repository.ResetTokenRepository;
import com.lpnu.shaggybeavers.service.ResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResetTokenServiceImpl extends CRUDServiceImpl<ResetToken, Long> implements ResetTokenService {

    private final ResetTokenRepository repository;

    @Override
    protected JpaRepository<ResetToken, Long> getRepository() {
        return repository;
    }

    @Override
    public ResetToken findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public boolean isResetTokenExpired(ResetToken resetToken) {
        return resetToken.getExpirationDate().isBefore(LocalDateTime.now());
    }

    @Override
    public void deleteResetToken(ResetToken resetToken) {
        repository.delete(resetToken);
    }

    @Override
    public ResetToken generateResetToken(User user) {
        ResetToken resetToken = new ResetToken();
        resetToken.setToken(UUID.randomUUID().toString());
        resetToken.setUser(user);
        resetToken.setExpirationDate(LocalDateTime.now().plusHours(ResetToken.EXPIRATION_IN_HOURS));
        return repository.save(resetToken);
    }

    @Override
    public void validateResetToken(String token) {
        ResetToken resetToken = this.findByToken(token);
        if (this.isResetTokenExpired(resetToken)) {
            throw new TokenException("Token is expired");
        }
    }

    @Override
    public ResetToken findByToken(String token) {
        return repository.findByToken(token)
                .orElseThrow(() -> new NotExistsObjectException("Reset token doesn't exist"));
    }

}
