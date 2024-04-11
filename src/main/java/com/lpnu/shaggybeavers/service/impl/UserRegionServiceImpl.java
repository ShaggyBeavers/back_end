package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.UserRegion;
import com.lpnu.shaggybeavers.repository.UserRegionRepository;
import com.lpnu.shaggybeavers.service.UserRegionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserRegionServiceImpl extends CRUDServiceImpl<UserRegion,Long> implements UserRegionService {

    private final UserRegionRepository repository;

    @Lazy
    public UserRegionServiceImpl(UserRegionRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<UserRegion, Long> getRepository () {
        return this.repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserRegion> findByUserIdAndRegionId(Long userId, Long regionId) {
        return repository.findByUserIdAndRegionId(userId, regionId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRegion> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public void deleteAllByUserId(Long userId) {
        repository.deleteAllByUserId(userId);
    }

    @Override
    @Transactional
    public boolean existsByRegionId(Long regionId) {
        return repository.existsByRegionId(regionId);
    }

}
