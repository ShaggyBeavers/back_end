package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.HistoricalPeriodCreateDTO;
import com.lpnu.shaggybeavers.dto.HistoricalPeriodDTO;
import com.lpnu.shaggybeavers.factory.HistoricalPeriodFactory;
import com.lpnu.shaggybeavers.service.HistoricalPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HistoricalPeriodFacade {

    private final HistoricalPeriodFactory historicalPeriodFactory;

    private final HistoricalPeriodService historicalPeriodService;

    @Transactional
    public void createHistoricalPeriod(HistoricalPeriodCreateDTO historicalPeriodCreateDTO) {
        historicalPeriodService.save(historicalPeriodFactory.toHistoricalPeriod(historicalPeriodCreateDTO));
    }

    @Transactional
    public HistoricalPeriodDTO findById(Long historicalPeriodId) {
        return historicalPeriodFactory.toHistoricalPeriodDTO(historicalPeriodService.findById(historicalPeriodId));
    }

    @Transactional
    public List<HistoricalPeriodDTO> findAll() {
        return historicalPeriodFactory.toHistoricalPeriodDTOs(historicalPeriodService.findAll());
    }

}
