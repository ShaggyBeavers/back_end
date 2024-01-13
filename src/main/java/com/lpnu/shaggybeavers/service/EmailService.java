package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.User;

public interface EmailService {

    void sendResetPasswordEmail(User user, String token);

}
