package com.bookstore.authservice.domain.service.impl;

import com.bookstore.authservice.application.dto.request.*;
import com.bookstore.authservice.application.dto.response.*;
import com.bookstore.authservice.application.utils.UserStatus;
import com.bookstore.authservice.domain.service.UserRoleService;
import com.bookstore.authservice.domain.service.UserService;
import com.bookstore.authservice.infrastructure.entity.RoleEntity;
import com.bookstore.authservice.infrastructure.entity.UserEntity;
import com.bookstore.authservice.infrastructure.repository.RoleRepository;
import com.bookstore.authservice.infrastructure.repository.UserRepository;
import com.nimbusds.oauth2.sdk.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private UserRoleService userRoleService;

    @Autowired
    public UserServiceImpl(
            BCryptPasswordEncoder passwordEncoder,
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserRoleService userRoleService
            ){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleService = userRoleService;
    }


    @Override
    public String createUser(CreateUserRequest createUserRequest) {

        String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());

        if (userRepository.existsByUserName(createUserRequest.getUserName())) {
            throw new RuntimeException("Username is already taken!!");
        }

        if (userRepository.existsByEmail(createUserRequest.getEmail())) {
            throw new RuntimeException("Email address already in use!!");
        }

        List<RoleEntity> validRoles = new ArrayList<>();

        createUserRequest.getRoleNames().forEach(roleName -> {
            roleRepository.findByRoleName(roleName).<Runnable>map(role -> () -> validRoles.add(role))
                    .orElse(() -> {
                    })
                    .run();
        });

        UserEntity user = UserEntity.builder()
                .userName(createUserRequest.getUserName())
                .email(createUserRequest.getEmail())
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .password(encodedPassword)
                .roles(new HashSet<>(validRoles))
                .build();
//        user.setCreateTime(new Date());
//        user.setUpdateTime(new Date());
//        user.setStatus(1);

        UserEntity savedUser = userRepository.save(user);

        return savedUser.getUserId();
    }

    @Override
    public GetUserResponse getUserByUserName(String userName) {

        Optional<UserEntity> userNameOrEmailOptional = userRepository
                .findByUserNameOrEmail(userName, userName);
        UserEntity userByUserName = userNameOrEmailOptional.orElseThrow(() ->
                new RuntimeException("UserName or Email doesn't exist!!")
        );

        return GetUserResponse.builder()
                .userId(userByUserName.getUserId())
                .userName(userByUserName.getUserName())
                .firstName(userByUserName.getFirstName())
                .lastName(userByUserName.getLastName())
                .email(userByUserName.getEmail())
                .roles(userByUserName.getRoles())
                .build();
    }

    @Override
    public GetUserResponse getUserByUserId(String userId) {
        Optional<UserEntity> userIdOptional = userRepository.findByUserId(userId);
        UserEntity userById = userIdOptional.orElseThrow(() ->
                new RuntimeException("UserName or Email doesn't exist!!")
        );

        return GetUserResponse.builder()
                .userId(userById.getUserId())
                .userName(userById.getUserName())
                .firstName(userById.getFirstName())
                .lastName(userById.getLastName())
                .email(userById.getEmail())
                .roles(userById.getRoles())
                .build();
    }

    @Override
    public GetUserInfoResponse getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) authentication.getPrincipal();

        GetUserResponse userByUserName = getUserByUserName(userName);

        return GetUserInfoResponse.builder()
                .userId(userByUserName.getUserId())
                .userName(userByUserName.getUserName())
                .firstName(userByUserName.getFirstName())
                .lastName(userByUserName.getLastName())
                .email(userByUserName.getEmail())
                .build();

    }

    @Override
    public void updateUserInfo(UpdateUserRequest updateUserRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) authentication.getPrincipal();

        Optional<UserEntity> userNameOrEmailOptional = userRepository.findByUserNameOrEmail(userName, userName);

        UserEntity userByUserName = userNameOrEmailOptional.orElseThrow(() ->
                new RuntimeException("UserName or Email doesn't exist!!")
        );

        if (updateUserRequest.getFirstName() != null) {
            userByUserName.setFirstName(updateUserRequest.getFirstName());
        }
        if (updateUserRequest.getLastName() != null) {
            userByUserName.setLastName(updateUserRequest.getLastName());
        }
        if (updateUserRequest.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(updateUserRequest.getPassword());
            userByUserName.setPassword(encodedPassword);
        }
        if (updateUserRequest.getEmail() != null) {
            userByUserName.setEmail(updateUserRequest.getEmail());
        }

        userRepository.save(userByUserName);
    }

    @Override
    public void deleteUserById(String userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) authentication.getPrincipal();
        GetUserResponse userByUserId = getUserByUserId(userId);

        if (userName.equals(userByUserId.getUserName())) {
            throw new RuntimeException("You cannot delete your own account!");
        }

        UserEntity UserEntity = userRepository.findByUserId(userId).get();
//        UserEntity.setStatus(0);
        userRepository.save(UserEntity);
    }

    @Override
    public List<GetUserResponse> getAllUsers() {

        Iterable<UserEntity> all = userRepository.findAll();
        List<GetUserResponse> allUsers = new ArrayList<>();
        all.iterator().forEachRemaining(u -> {
            GetUserResponse userResponse = GetUserResponse.builder()
                    .userId(u.getUserId())
                    .userName(u.getUserName())
                    .firstName(u.getFirstName())
                    .lastName(u.getLastName())
                    .email(u.getEmail())
                    .roles(u.getRoles())
                    .build();
            allUsers.add(userResponse);
        });

        return allUsers;
    }

    @Override
    public void updateUser(String userId, UpdateUserRequestFromAdmin updateUserRequestFromAdmin) {

        Optional<UserEntity> existingUser = userRepository.findByUserId(userId);

        UserEntity user = existingUser.orElseThrow(() ->
                new RuntimeException("UserId doesn't exist!!")
        );

        if (updateUserRequestFromAdmin.getFirstName() != null) {
            user.setFirstName(updateUserRequestFromAdmin.getFirstName());
        }
        if (updateUserRequestFromAdmin.getLastName() != null) {
            user.setLastName(updateUserRequestFromAdmin.getLastName());
        }
        if (updateUserRequestFromAdmin.getEmail() != null) {
            user.setEmail(updateUserRequestFromAdmin.getEmail());
        }

        if (user.getRoles().size() > 0) {
            MapUserToRolesRequest mapUserToRolesRequest = new MapUserToRolesRequest();
            List<String> existingRoles = user.getRoles().stream().map(RoleEntity::getRoleName).collect(Collectors.toList());
            mapUserToRolesRequest.setRoleNames(existingRoles);
            userRoleService.removeRolesFromUser(user.getUserName(), mapUserToRolesRequest);
        }

        if (updateUserRequestFromAdmin.getRoles().size() > 0) {
            MapUserToRolesRequest mapUserToRolesRequest = new MapUserToRolesRequest();
            mapUserToRolesRequest.setRoleNames(updateUserRequestFromAdmin.getRoles());
            userRoleService.mapUserToRoles(user.getUserName(), mapUserToRolesRequest);
        }

        userRepository.save(user);
    }

}