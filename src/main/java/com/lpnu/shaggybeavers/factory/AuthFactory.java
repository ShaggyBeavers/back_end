package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.UserDTO;
import com.lpnu.shaggybeavers.dto.auth.RegistrationDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFactory {

    private final EntityMapper entityMapper;

    public User RegistrationDtoToUser(RegistrationDTO dto) {
        return entityMapper.RegistrationDtoToUser(dto);
    }

}
