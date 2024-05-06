package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${domain:localhost}")
    private String domain;

    @Value("${spring.mail.username}")
    private String mailUsername;

    private final JavaMailSender javaMailSender;

    @Override
    public void sendResetPasswordEmail(User user, String token) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(mailUsername);
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Reset password");

        simpleMailMessage.setText(
                "Click the following link to reset your password: " +
                "https://%s/change_password?token=%s".formatted(domain, token));

        javaMailSender.send(simpleMailMessage);
    }

}
