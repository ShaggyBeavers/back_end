package com.lpnu.shaggybeavers.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class RecoveredRelicInfoCreateEditDTO {

    private String locationSource;

    private LocalDate previousSearchInfo;

    private String returnProcess;

    private LocalDate returnDate;

    private String courtDecision;

}
