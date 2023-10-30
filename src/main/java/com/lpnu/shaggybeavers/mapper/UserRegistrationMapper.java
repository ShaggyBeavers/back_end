package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.auth.RegistrationDTO;
import com.lpnu.shaggybeavers.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserRegistrationMapper {
    public RegistrationDTO userToDTO(User user);

    public User DTOtoUser(RegistrationDTO dto);
}
