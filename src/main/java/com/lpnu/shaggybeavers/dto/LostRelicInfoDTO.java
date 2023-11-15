package com.lpnu.shaggybeavers.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class LostRelicInfoDTO {

    private String lossWay;

    private LocalDate lossTime;

    private String museumName;

    private String probableLocation;

}
