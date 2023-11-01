package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.HistoricalPeriod;
import com.lpnu.shaggybeavers.repository.HistoricalPeriodRepository;
import com.lpnu.shaggybeavers.service.HistoricalPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoricalPeriodServiceImpl extends CRUDServiceImpl<HistoricalPeriod, Long> implements HistoricalPeriodService {

    private final HistoricalPeriodRepository historicalPeriodRepository;

    @Override
    protected JpaRepository<HistoricalPeriod, Long> getRepository() {
        return this.historicalPeriodRepository;
    }
}
