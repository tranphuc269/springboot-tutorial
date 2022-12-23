package com.bookstore.authservice.domain.service.impl;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.bookstore.authservice.application.dto.request.MapRoleToUsersRequest;
import com.bookstore.authservice.application.dto.request.MapUserToRolesRequest;
import com.bookstore.authservice.domain.service.UserRoleService;
import com.bookstore.authservice.infrastructure.entity.RoleEntity;
import com.bookstore.authservice.infrastructure.entity.UserEntity;
import com.bookstore.authservice.infrastructure.repository.RoleRepository;
import com.bookstore.authservice.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void mapUserToRoles(String userNameOrEmail, MapUserToRolesRequest mapUserToRolesRequest) {

        Optional<UserEntity> userNameOrEmailOptional = userRepository
                .findByUserNameOrEmail(userNameOrEmail, userNameOrEmail);

        UserEntity user = userNameOrEmailOptional.orElseThrow(() ->
                new RuntimeException("UserNameOrEmail doesn't exist!!")
        );

        Set<RoleEntity> roles = user.getRoles();


        mapUserToRolesRequest.getRoleNames().forEach(roleName -> {

            roleRepository.findByRoleName(roleName).<Runnable>map(role -> () -> roles.add(role))
                    .orElse(() -> {
                    })
                    .run();
        });

        user.setRoles(roles);

        userRepository.save(user);


    }

    @Override
    public void removeRolesFromUser(String userNameOrEmail, MapUserToRolesRequest mapUserToRolesRequest) {

        Optional<UserEntity> userNameOrEmailOptional = userRepository
                .findByUserNameOrEmail(userNameOrEmail, userNameOrEmail);

        UserEntity user = userNameOrEmailOptional.orElseThrow(() ->
                new RuntimeException("UserNameOrEmail doesn't exist!!")
        );

        Set<RoleEntity> roles = user.getRoles();

        mapUserToRolesRequest.getRoleNames().forEach(roleName -> {
            //if role exists add to list and persist, else add to error response persist valid roles and send
            // response containing invalid roles.
            roleRepository.findByRoleName(roleName).<Runnable>map(role -> () -> roles.remove(role))
                    .orElse(() -> {
                    })
                    .run();
        });

        user.setRoles(roles);

        userRepository.save(user);
    }

    @Override
    public void mapRoleToUsers(String roleName, MapRoleToUsersRequest mapRoleToUsersRequest) {

        RoleEntity role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role doesn't exist!!"));

        mapRoleToUsersRequest.getUserNames().forEach(userName -> {
            userRepository.findByUserName(userName).<Runnable>map(user -> () -> role.addUser(user))
                    .orElse(() -> {

                    })
                    .run();
        });

        roleRepository.save(role);
    }
}