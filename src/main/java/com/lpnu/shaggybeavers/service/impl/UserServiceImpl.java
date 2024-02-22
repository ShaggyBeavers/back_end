package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.exception.DuplicateException;
import com.lpnu.shaggybeavers.exception.NotExistsObjectException;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.repository.UserRepository;
import com.lpnu.shaggybeavers.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDServiceImpl<User,Long> implements UserService {

    private final UserRepository repository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected JpaRepository<User, Long> getRepository () {
        return this.repository;
    }

    @Override
    @Transactional
    public List<User> findAll(Specification<User> specification) {
        return repository.findAll(specification);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NotExistsObjectException("User with email %s doesn't exist".formatted(email)));
    }

    @Override
    @Transactional
    public void changePassword(User user, String password) {
        user.setPassword(bCryptPasswordEncoder.encode(password));
        repository.save(user);
    }

    @Override
    @Transactional
    public void banUnban(User user) {
        user.setBan(!user.isBan());
        repository.save(user);
    }

    @Override
    public boolean emailIsPresent(String email) {
        return repository.findByEmail(email).isPresent();
    }

    @Override
    @Transactional
    public User save(User user) {
        if (repository.findByEmail(user.getEmail()).isPresent())  {
            throw new DuplicateException("This email is already registered");
        }
        return repository.save(user);
    }

//    @Override
//    @Transactional
//    public User update(User newUser) {
//        if (repository.findByEmail(newUser.getEmail()).isPresent())  {
//            throw new DuplicateException("This email is already registered");
//        }
//        User oldUser = this.findById(newUser.getId());
//        BeanUtils.copyProperties(newUser,oldUser);
//        return repository.saveAndFlush(oldUser);
//    }

}
