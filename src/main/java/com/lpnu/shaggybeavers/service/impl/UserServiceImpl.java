package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.repository.UserRepository;
import com.lpnu.shaggybeavers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDServiceImpl<User,Long> implements UserService {

    private final UserRepository repository;

    @Override
    protected JpaRepository<User, Long> getRepository () {
        return this.repository;
    }

    @Override
    public List<User> findAll(Specification<User> specification) {
        return repository.findAll(specification);
    }

}
