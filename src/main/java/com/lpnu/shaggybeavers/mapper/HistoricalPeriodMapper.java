package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.HistoricalPeriodCreateDTO;
import com.lpnu.shaggybeavers.dto.HistoricalPeriodDTO;
import com.lpnu.shaggybeavers.model.HistoricalPeriod;

import java.util.List;

public interface HistoricalPeriodMapper {

    HistoricalPeriod toHistoricalPeriod(HistoricalPeriodCreateDTO historicalPeriodCreateDTO);

    HistoricalPeriodDTO toHistoricalPeriodDTO(HistoricalPeriod historicalPeriod);

    List<HistoricalPeriodDTO> toHistoricalPeriodDTOs(List<HistoricalPeriod> historicalPeriods);

}
