package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.RoleFactory;
import com.lpnu.shaggybeavers.model.Role;
import com.lpnu.shaggybeavers.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleFacade {

    private final RoleFactory roleFactory;

    private final RoleService roleService;

    public boolean existsByName(String name) {
        return roleService.existsByName(name);
    }

    public void save(String name) {
        roleService.save(roleFactory.toRole(name));
    }
}
