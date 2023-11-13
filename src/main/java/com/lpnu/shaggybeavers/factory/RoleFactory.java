package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleFactory {

    private final EntityMapper entityMapper;

    public Role toRole(String name) {
        return entityMapper.toRole(name);
    }
}
