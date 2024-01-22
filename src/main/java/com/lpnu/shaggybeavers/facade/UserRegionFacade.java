package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.UserRegionFactory;
import com.lpnu.shaggybeavers.model.UserRegion;
import com.lpnu.shaggybeavers.service.UserRegionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRegionFacade {

    private final UserRegionFactory userRegionFactory;

    private final UserRegionService userRegionService;

    @Transactional
    public void save(UserRegion userRegion) {
        userRegionService.save(userRegion);
    }

    @Transactional
    public Optional<UserRegion> findByUserIdAndRegionId(Long userId, Long regionId) {
        return userRegionService.findByUserIdAndRegionId(userId, regionId);
    }
}
