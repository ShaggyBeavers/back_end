package com.lpnu.shaggybeavers.filter;

import com.lpnu.shaggybeavers.domain.RelicStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RelicFilter {

    private List<String> historicalPeriods;

    private List<RelicStatus> statuses;

    private List<String> techniques;

    private List<String> collections;

    private List<String> categories;

    private Boolean imageUrl;

}
