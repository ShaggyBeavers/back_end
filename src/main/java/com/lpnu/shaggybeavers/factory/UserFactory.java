package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.UserDTO;
import com.lpnu.shaggybeavers.dto.UserEditDTO;
import com.lpnu.shaggybeavers.dto.UserProfileDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final EntityMapper entityMapper;

    public UserProfileDTO toUserProfileDTO(User user) {
        return entityMapper.toUserProfileDTO(user);
    }

    public List<UserDTO> toUserDTOList(List<User> users) {
        return entityMapper.toUserDTOList(users);
    }

    public User update(User user, UserEditDTO userEditDTO) { return entityMapper.update(user, userEditDTO); }

    public UserDTO toUserDTO(User user) {
        return entityMapper.toUserDTO(user);
    }

}
