package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.exception.AssociationException;
import com.lpnu.shaggybeavers.model.HistoricalPeriod;
import com.lpnu.shaggybeavers.repository.HistoricalPeriodRepository;
import com.lpnu.shaggybeavers.service.HistoricalPeriodService;
import com.lpnu.shaggybeavers.service.RelicInfoService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoricalPeriodServiceImpl extends CRUDServiceImpl<HistoricalPeriod, Long> implements HistoricalPeriodService {

    private final RelicInfoService relicInfoService;

    private final HistoricalPeriodRepository historicalPeriodRepository;

    @Lazy
    public HistoricalPeriodServiceImpl(RelicInfoService relicInfoService, HistoricalPeriodRepository historicalPeriodRepository) {
        this.relicInfoService = relicInfoService;
        this.historicalPeriodRepository = historicalPeriodRepository;
    }

    @Override
    protected JpaRepository<HistoricalPeriod, Long> getRepository() {
        return this.historicalPeriodRepository;
    }

    @Override
    @Transactional
    public HistoricalPeriod deleteById(Long historicalPeriodId) {
        HistoricalPeriod historicalPeriod = findById(historicalPeriodId);

        if (relicInfoService.existsByHistoricalPeriodId(historicalPeriodId)) {
            throw new AssociationException("The historical period is associated with a relic info");
        }

        historicalPeriodRepository.delete(historicalPeriod);
        return historicalPeriod;
    }

}
