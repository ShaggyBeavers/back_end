package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.RelicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelicInfoRepository extends JpaRepository<RelicInfo, Long> {
}
