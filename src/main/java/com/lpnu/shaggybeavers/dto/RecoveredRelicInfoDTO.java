package com.lpnu.shaggybeavers.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class RecoveredRelicInfoDTO {

    private String locationSource;

    private String returnProcess;

    private LocalDate returnDate;

    private String courtDecision;

}
