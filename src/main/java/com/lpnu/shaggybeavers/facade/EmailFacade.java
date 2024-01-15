package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.EmailFactory;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailFacade {

    private final EmailFactory emailFactory;

    private final EmailService emailService;

    public void sendResetPasswordEmail(User user, String token) {
        emailService.sendResetPasswordEmail(user, token);
    }

}
