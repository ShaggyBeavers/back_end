package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.UserRegion;

import java.util.Optional;

public interface UserRegionService extends CRUDService<UserRegion,Long> {

    Optional<UserRegion> findByUserIdAndRegionId(Long userId, Long regionId);

}
