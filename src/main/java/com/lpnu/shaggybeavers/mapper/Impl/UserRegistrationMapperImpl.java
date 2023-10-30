package com.lpnu.shaggybeavers.mapper.Impl;

import com.lpnu.shaggybeavers.dto.auth.RegistrationDTO;
import com.lpnu.shaggybeavers.mapper.UserRegistrationMapper;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class UserRegistrationMapperImpl implements UserRegistrationMapper {

    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public RegistrationDTO userToDTO(User user) {
        return new RegistrationDTO(user.getFirstName(),user.getLastName(), user.getEmail(), user.getPassword());
    }

    @Override
    public User DTOtoUser(RegistrationDTO dto) {
        var user = new User();
        user.setEmail(dto.getEmail());
        user.setRole(roleService.findByName("USER"));
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        return user;
    }
}
