package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.Role;
import com.lpnu.shaggybeavers.repository.RoleRepository;
import com.lpnu.shaggybeavers.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends CRUDServiceImpl<Role,Long> implements RoleService {

    private final RoleRepository repository;

    @Override
    protected JpaRepository<Role, Long> getRepository () {
        return this.repository;
    }
}
