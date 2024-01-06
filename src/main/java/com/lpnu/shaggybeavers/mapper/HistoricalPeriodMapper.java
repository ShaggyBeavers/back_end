package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.HistoricalPeriodCreateDTO;
import com.lpnu.shaggybeavers.model.HistoricalPeriod;

public interface HistoricalPeriodMapper {

    HistoricalPeriod toHistoricalPeriod(HistoricalPeriodCreateDTO historicalPeriodCreateDTO);

}
