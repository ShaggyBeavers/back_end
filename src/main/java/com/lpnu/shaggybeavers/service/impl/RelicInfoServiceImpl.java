package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.RelicInfo;
import com.lpnu.shaggybeavers.repository.RelicInfoRepository;
import com.lpnu.shaggybeavers.service.RelicInfoService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RelicInfoServiceImpl extends CRUDServiceImpl<RelicInfo, Long> implements RelicInfoService {

    private final RelicInfoRepository relicInfoRepository;

    @Lazy
    public RelicInfoServiceImpl(RelicInfoRepository relicInfoRepository) {
        this.relicInfoRepository = relicInfoRepository;
    }

    @Override
    public JpaRepository<RelicInfo, Long> getRepository() {
        return this.relicInfoRepository;
    }

    @Override
    @Transactional
    public boolean existsByHistoricalPeriodId(Long historicalPeriodId) {
        return relicInfoRepository.existsByHistoricalPeriodId(historicalPeriodId);
    }

    @Override
    @Transactional
    public boolean existsByTechniqueId(Long techniqueId) {
        return relicInfoRepository.existsByTechniqueId(techniqueId);
    }

}
