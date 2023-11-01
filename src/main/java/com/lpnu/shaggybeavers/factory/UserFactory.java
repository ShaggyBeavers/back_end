package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.UserDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final EntityMapper entityMapper;

    public UserDTO userToDTO(User user) {
        return entityMapper.toUserDTO(user);
    }
}
