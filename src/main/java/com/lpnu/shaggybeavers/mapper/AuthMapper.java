package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.auth.RegistrationDTO;
import com.lpnu.shaggybeavers.model.User;

public interface AuthMapper {

    User RegistrationDtoToUser(RegistrationDTO dto);
}
