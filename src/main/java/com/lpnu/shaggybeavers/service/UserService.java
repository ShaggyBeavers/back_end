package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.User;

public interface UserService extends CRUDService<User,Long> {
    User findByEmail(String email);
}
