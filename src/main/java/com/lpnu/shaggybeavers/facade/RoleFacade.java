package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.RoleFactory;
import com.lpnu.shaggybeavers.model.Role;
import com.lpnu.shaggybeavers.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleFacade {

    private final RoleFactory roleFactory;

    private final RoleService roleService;

    @Transactional
    public boolean existsByName(String name) {
        return roleService.existsByName(name);
    }

    @Transactional
    public void save(String name) {
        roleService.save(roleFactory.toRole(name));
    }

    @Transactional
    public Role findByName(String name) {
        return roleService.findByName(name);
    }
}
