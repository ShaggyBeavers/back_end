package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.LostRelicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostRelicInfoRepository extends JpaRepository<LostRelicInfo, Long> {
}
