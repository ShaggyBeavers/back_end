package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.exception.NotExistsObjectException;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.repository.UserRepository;
import com.lpnu.shaggybeavers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDServiceImpl<User,Long> implements UserService {

    private final UserRepository repository;

    @Override
    protected JpaRepository<User, Long> getRepository () {
        return this.repository;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NotExistsObjectException("User does not exist"));
    }
}
