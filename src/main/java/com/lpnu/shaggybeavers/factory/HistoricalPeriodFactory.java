package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.HistoricalPeriodCreateDTO;
import com.lpnu.shaggybeavers.dto.HistoricalPeriodDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.HistoricalPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HistoricalPeriodFactory {

    private final EntityMapper entityMapper;

    public HistoricalPeriod toHistoricalPeriod(HistoricalPeriodCreateDTO historicalPeriodCreateDTO) {
        return entityMapper.toHistoricalPeriod(historicalPeriodCreateDTO);
    }

    public HistoricalPeriodDTO toHistoricalPeriodDTO(HistoricalPeriod historicalPeriod) {
        return entityMapper.toHistoricalPeriodDTO(historicalPeriod);
    }

    public List<HistoricalPeriodDTO> toHistoricalPeriodDTOs(List<HistoricalPeriod> historicalPeriods) {
        return entityMapper.toHistoricalPeriodDTOs(historicalPeriods);
    }

}
