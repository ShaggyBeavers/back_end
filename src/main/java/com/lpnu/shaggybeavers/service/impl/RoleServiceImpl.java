package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.exception.NotExistsObjectException;
import com.lpnu.shaggybeavers.model.Role;
import com.lpnu.shaggybeavers.repository.RoleRepository;
import com.lpnu.shaggybeavers.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends CRUDServiceImpl<Role,Long> implements RoleService {

    private final RoleRepository repository;

    @Override
    protected JpaRepository<Role, Long> getRepository () {
        return this.repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Role findByName (String name) {
        return repository.findByName(name).orElseThrow( () -> new NotExistsObjectException("Role with %s name doesn't exist".formatted(name)));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

}
