package com.bookstore.authservice.domain.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bookstore.authservice.domain.service.AppUserDetailsService;
import com.bookstore.authservice.infrastructure.entity.UserEntity;
import com.bookstore.authservice.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AppUserDetailsServiceImpl implements AppUserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public AppUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {

        Optional<UserEntity> userOptional = userRepository
                .findByUserNameOrEmail(userNameOrEmail, userNameOrEmail);

        UserEntity user = userOptional.orElseThrow(() ->
                new UsernameNotFoundException(String.format("The username or email Id %s doesn't exist",
                        userNameOrEmail))
        );

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getUserName(), user.getPassword(), authorities);

        return userDetails;
    }
}