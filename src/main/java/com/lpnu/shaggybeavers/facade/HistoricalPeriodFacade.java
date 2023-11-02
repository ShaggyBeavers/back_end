package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.HistoricalPeriodFactory;
import com.lpnu.shaggybeavers.service.HistoricalPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HistoricalPeriodFacade {

    private final HistoricalPeriodFactory historicalPeriodFactory;

    private final HistoricalPeriodService historicalPeriodService;

}
