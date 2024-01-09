package com.lpnu.shaggybeavers.dto;

import com.lpnu.shaggybeavers.model.HistoricalPeriod;
import com.lpnu.shaggybeavers.model.Technique;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RelicInfoCreateEditDTO {


    private Technique technique;

    private HistoricalPeriod historicalPeriod;

    private String dimensions;

    private String marks;

    private String labels;

    private String signatures;

    private String restoration;

    private String appraisedValue;

    private String insuranceValue;

    private String annotation;

    private LostRelicInfoCreateEditDTO lostRelicInfoCreateEditDTO;

    private RecoveredRelicInfoCreateEditDTO recoveredRelicInfoCreateEditDTO;
}
