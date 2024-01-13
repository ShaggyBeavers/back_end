package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.exception.NotExistsObjectException;
import com.lpnu.shaggybeavers.model.ResetToken;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.repository.UserRepository;
import com.lpnu.shaggybeavers.service.EmailService;
import com.lpnu.shaggybeavers.service.ResetTokenService;
import com.lpnu.shaggybeavers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDServiceImpl<User,Long> implements UserService {

    private final UserRepository repository;

    private final ResetTokenService resetTokenService;

    private final EmailService emailService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected JpaRepository<User, Long> getRepository () {
        return this.repository;
    }

    @Override
    public List<User> findAll(Specification<User> specification) {
        return repository.findAll(specification);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NotExistsObjectException("User with email %s doesn't exist".formatted(email)));
    }

    @Override
    public ResetToken findResetTokenByUserId(Long userId) {
        return resetTokenService.findResetTokenByUserId(userId);
    }

    @Override
    public boolean isResetTokenExpired(ResetToken resetToken) {
        return resetTokenService.isResetTokenExpired(resetToken);
    }

    @Override
    public void sendResetPasswordEmail(User user, String token) {
        emailService.sendResetPasswordEmail(user, token);
    }

    @Override
    public void deleteResetToken(ResetToken resetToken) {
        resetTokenService.deleteResetToken(resetToken);
    }

    @Override
    public ResetToken generateResetToken(User user) {
        return resetTokenService.generateResetToken(user);
    }

    @Override
    public void validateResetToken(String token) {
        resetTokenService.validateResetToken(token);
    }

    @Override
    public void changePassword(String token, String password) {
        ResetToken resetToken = resetTokenService.findByToken(token);
        User user = resetToken.getUser();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        repository.save(user);
    }

}
