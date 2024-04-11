package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.RelicInfo;

public interface RelicInfoService extends CRUDService<RelicInfo, Long> {
    boolean existsByHistoricalPeriodId(Long historicalPeriodId);

    boolean existsByTechniqueId(Long techniqueId);
}
