package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.UserDTO;
import com.lpnu.shaggybeavers.model.User;

public interface UserMapper {

    UserDTO toUserDTO(User user);

}
