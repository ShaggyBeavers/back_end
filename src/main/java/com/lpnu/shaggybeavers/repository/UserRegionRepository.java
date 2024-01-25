package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.UserRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRegionRepository extends JpaRepository<UserRegion,Long> {

    Optional<UserRegion> findByUserIdAndRegionId(Long userId, Long regionId);

}
