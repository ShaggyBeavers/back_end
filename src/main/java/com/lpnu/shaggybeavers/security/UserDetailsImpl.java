package com.lpnu.shaggybeavers.security;

import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username).orElseThrow( () -> new UsernameNotFoundException("User with email %s doesn't exist".formatted(username)) );

        return new UserPrincipal(user);
    }

}
