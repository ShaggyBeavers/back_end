package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.RecoveredRelicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoveredRelicInfoRepository extends JpaRepository<RecoveredRelicInfo, Long> {
}
