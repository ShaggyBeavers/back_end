package com.lpnu.shaggybeavers.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LostRelicInfoCreateEditDTO {

    private String lossWay;

    private String lossTime;

    private Long museumId;

    private String probableLocation;

}
