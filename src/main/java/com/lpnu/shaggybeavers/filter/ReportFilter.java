package com.lpnu.shaggybeavers.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ReportFilter {

    public Long userId;

    public Set<Long> regionIds;

    public Set<Long> categoryIds;

}
