package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.UserRegion;
import com.lpnu.shaggybeavers.repository.UserRegionRepository;
import com.lpnu.shaggybeavers.service.UserRegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRegionServiceImpl extends CRUDServiceImpl<UserRegion,Long> implements UserRegionService {

    private final UserRegionRepository repository;

    @Override
    protected JpaRepository<UserRegion, Long> getRepository () {
        return this.repository;
    }

    @Override
    public Optional<UserRegion> findByUserIdAndRegionId(Long userId, Long regionId) {
        return repository.findByUserIdAndRegionId(userId, regionId);
    }

}
