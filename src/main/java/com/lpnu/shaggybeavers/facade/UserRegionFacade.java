package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.exception.DuplicateException;
import com.lpnu.shaggybeavers.factory.UserRegionFactory;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.model.UserRegion;
import com.lpnu.shaggybeavers.service.UserRegionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class UserRegionFacade {

    private final UserRegionFactory userRegionFactory;

    private final UserRegionService userRegionService;

    private final RegionFacade regionFacade;

    @Transactional
    public void create(User user, Set<Long> regionIds) {
        regionIds.forEach(regionId -> {
                    userRegionService.findByUserIdAndRegionId(user.getId(), regionId)
                            .ifPresent(value -> { throw new DuplicateException("UserRegion object already exists"); });

                    UserRegion newUserRegion = new UserRegion();
                    newUserRegion.setUser(user);
                    newUserRegion.setRegion(regionFacade.findById(regionId));
                    userRegionService.save(newUserRegion);
                });
    }

    @Transactional
    public Set<Long> getRegionIdsByUserId(Long regionalModeratorId) {
        Set<Long> regionIds = new HashSet<>();
        userRegionService.findAllByUserId(regionalModeratorId).forEach(userRegion -> {
            regionIds.add(userRegion.getId());
        });
        return regionIds;
    }
}
