package com.lpnu.shaggybeavers.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class UserFilter {

    private String roleName;

    private Set<Long> regionIds;

}
