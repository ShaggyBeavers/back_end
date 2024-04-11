package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.UserCategory;
import com.lpnu.shaggybeavers.repository.UserCategoryRepository;
import com.lpnu.shaggybeavers.service.UserCategoryService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserCategoryServiceImpl extends CRUDServiceImpl<UserCategory,Long> implements UserCategoryService {

    private final UserCategoryRepository repository;

    @Lazy
    public UserCategoryServiceImpl(UserCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<UserCategory, Long> getRepository () {
        return this.repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserCategory> findByUserIdAndCategoryId(Long userId, Long categoryId) {
        return repository.findByUserIdAndCategoryId(userId, categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserCategory> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public void deleteAllByUserId(Long userId) {
        repository.deleteAllByUserId(userId);
    }

    @Override
    @Transactional
    public boolean existsByCategoryId(Long categoryId) {
        return repository.existsByCategoryId(categoryId);
    }

}
