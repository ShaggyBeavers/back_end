package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.UserDTO;
import com.lpnu.shaggybeavers.dto.UserEditDTO;
import com.lpnu.shaggybeavers.dto.UserProfileDTO;
import com.lpnu.shaggybeavers.model.User;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface UserMapper {

    UserProfileDTO toUserProfileDTO(User user);

    @Mapping(source = "userCategories", target = "categories")
    @Mapping(source = "userRegions", target = "regions")
    UserDTO toUserDTO(User user);

    List<UserDTO> toUserDTOList(List<User> users);

    User update(@MappingTarget User user, UserEditDTO userEditDTO);
}
