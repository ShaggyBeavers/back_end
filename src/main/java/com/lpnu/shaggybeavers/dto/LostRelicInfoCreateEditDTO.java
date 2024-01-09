package com.lpnu.shaggybeavers.dto;

import com.lpnu.shaggybeavers.model.Museum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class LostRelicInfoCreateEditDTO {

    private String lossWay;

    private LocalDate lossTime;

    private Museum museum;

    private String probableLocation;

}
