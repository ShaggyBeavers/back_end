package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.UserProfileDTO;
import com.lpnu.shaggybeavers.model.User;

public interface UserMapper {

    UserProfileDTO toUserDTO(User user);

}
