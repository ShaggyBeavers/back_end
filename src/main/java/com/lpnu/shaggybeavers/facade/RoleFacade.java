package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.RoleFactory;
import com.lpnu.shaggybeavers.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleFacade {

    private final RoleFactory roleFactory;

    private final RoleService roleService;

}
