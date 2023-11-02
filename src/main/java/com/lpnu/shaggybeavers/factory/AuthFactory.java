package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.JwtDTO;
import com.lpnu.shaggybeavers.dto.auth.RegistrationDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFactory {

    private final EntityMapper entityMapper;

    public User toUser(RegistrationDTO dto) {
        return entityMapper.toUser(dto);
    }

    public JwtDTO toJwtDTO(String accessToken) {
        return entityMapper.toJwtDTO(accessToken);
    }
}
