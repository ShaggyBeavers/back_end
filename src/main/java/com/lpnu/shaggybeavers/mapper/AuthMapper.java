package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.JwtDTO;
import com.lpnu.shaggybeavers.dto.auth.RegistrationDTO;
import com.lpnu.shaggybeavers.model.User;
import org.mapstruct.Mapping;

public interface AuthMapper {

    User toUser(RegistrationDTO dto);
    JwtDTO toJwtDTO(String accessToken);
}
