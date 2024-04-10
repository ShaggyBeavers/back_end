package com.lpnu.shaggybeavers.filter;

import com.lpnu.shaggybeavers.domain.RoleEnum;
import com.lpnu.shaggybeavers.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ReportFilter {

    public Long userId;

    public Set<Long> regionIds;

    public Set<Long> categoryIds;

    private Boolean file;

    private RoleEnum userRole;

}
