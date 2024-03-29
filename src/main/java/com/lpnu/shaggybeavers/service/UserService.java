package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface UserService extends CRUDService<User,Long> {

    List<User> findAll(Specification<User> specification);

    User findByEmail(String email);

    void changePassword(User user, String password);

    void banUnban(User user);

    boolean emailIsPresent(String email);
}
