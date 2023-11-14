package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.Role;

public interface RoleService extends CRUDService<Role,Long> {
    Role findByName (String name);

    boolean existsByName(String name);
}
