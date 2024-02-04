package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.UserCategory;
import com.lpnu.shaggybeavers.repository.UserCategoryRepository;
import com.lpnu.shaggybeavers.service.UserCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCategoryServiceImpl extends CRUDServiceImpl<UserCategory,Long> implements UserCategoryService {

    private final UserCategoryRepository repository;

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

}
