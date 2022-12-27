package com.bookstore.authservice.domain.service.impl;

import com.bookstore.authservice.application.dto.request.CreateOAuthClientRequest;
import com.bookstore.authservice.application.dto.request.SignInRequest;
import com.bookstore.authservice.application.dto.request.SignUpRequest;
import com.bookstore.authservice.application.dto.response.CreateOAuthClientResponse;
import com.bookstore.authservice.application.dto.response.CreateUserResponse;
import com.bookstore.authservice.application.dto.response.JwtAuthenticationResponse;
import com.bookstore.authservice.application.utils.JwtTokenUtil;
import com.bookstore.authservice.domain.service.AuthService;
import com.bookstore.authservice.infrastructure.data.entity.OAuthClient;
import com.bookstore.authservice.infrastructure.data.entity.RoleEntity;
import com.bookstore.authservice.infrastructure.data.entity.UserEntity;
import com.bookstore.authservice.infrastructure.data.repository.OAuthClientRepository;
import com.bookstore.authservice.infrastructure.data.repository.RoleRepository;
import com.bookstore.authservice.infrastructure.data.repository.UserRepository;
import com.bookstore.authservice.infrastructure.kafka.AuthKafkaProducer;
import com.bookstore.common.infrastructure.kafka.user_notification.KafkaUpdateNotificationFromUser;
import com.bookstore.common.utils.KafkaTopicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;


@Service
public class AuthServiceImpl implements AuthService {


    private BCryptPasswordEncoder passwordEncoder;

    private UserRepository userRepository;


    private RoleRepository roleRepository;


    private OAuthClientRepository oAuthClientRepository;

    private AuthenticationManager authenticationManager;

    private AuthKafkaProducer kafkaProducer;

    @Autowired
    public AuthServiceImpl(
            BCryptPasswordEncoder passwordEncoder,
            UserRepository userRepository,
            RoleRepository roleRepository,
            OAuthClientRepository oAuthClientRepository,
            AuthenticationManager authenticationManager,
            AuthKafkaProducer kafkaProducer

    ) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.oAuthClientRepository = oAuthClientRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Value("${security.jwt.key-store}")
    private Resource keyStore;

    @Value("${security.jwt.key-store-password}")
    private String keyStorePassword;

    @Value("${security.jwt.key-pair-alias}")
    private String keyPairAlias;

    @Value("${security.jwt.key-pair-password}")
    private String keyPairPassword;

    @Value("${security.jwt.public-key}")
    private Resource publicKey;

    @Override
    public CreateOAuthClientResponse createOAuthClient(
            CreateOAuthClientRequest createOAuthClientRequest) {

        //Generate client secret.
        String clientSecret = UUID.randomUUID().toString();
        String encode = passwordEncoder.encode(clientSecret);

        OAuthClient oAuthClient = OAuthClient.builder()
                .client_secret(encode)
                .authorities(String.join(",", createOAuthClientRequest.getAuthorities()))
                .authorized_grant_types(
                        String.join(",", createOAuthClientRequest.getAuthorized_grant_types()))
                .scope(String.join(",", createOAuthClientRequest.getScope()))
                .resource_ids(String.join(",", createOAuthClientRequest.getResource_ids()))
                .build();

        OAuthClient saved = oAuthClientRepository.save(oAuthClient);

        return CreateOAuthClientResponse.builder()
                .client_id(saved.getClient_id())
                .client_secret(clientSecret)
                .build();

    }

    @Override
    public CreateUserResponse registerUser(SignUpRequest signUpRequest) {

        if (userRepository.existsByUserName(signUpRequest.getUserName())) {
            throw new RuntimeException("Username is already taken!!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email address already in use!!");
        }

        // Creating user's account
        UserEntity user = UserEntity
                .builder()
                .userName(signUpRequest.getUserName())
                .password(signUpRequest.getPassword())
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .build();


        user.setPassword(passwordEncoder.encode(user.getPassword()));

        RoleEntity userRole = roleRepository.findByRoleName("STANDARD_USER")
                .orElseThrow(() -> new RuntimeException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        UserEntity savedUser =
                userRepository.save(user);
        // kafka send  to notification service
        kafkaProducer.send(KafkaTopicUtils.TOPIC_UPDATE_NOTIFICATION_INFO_FROM_USER,
                KafkaUpdateNotificationFromUser
                        .builder()
                        .userId(savedUser.getUserId())
                        .email(savedUser.getEmail())
                        .userName(savedUser.getUserName())
                        .firebaseToken("None")
                        .build());
        return CreateUserResponse.builder()
                .userId(savedUser.getUserId())
                .userName(savedUser.getUserName())
                .build();

    }

    @Override
    public JwtAuthenticationResponse loginUser(SignInRequest signInRequest) {
        if (userRepository.existsByUserName(signInRequest.getUsernameOrEmail())) {
            throw new RuntimeException("Username is already taken!!");
        }

        if (userRepository.existsByEmail(signInRequest.getUsernameOrEmail())) {
            throw new RuntimeException("Email address already in use!!");
        }

        Optional<UserEntity> userEntity = userRepository.findByUserNameOrEmail(signInRequest.getUsernameOrEmail(), signInRequest.getUsernameOrEmail());
        if (userEntity.isEmpty()) {
            throw new RuntimeException("Account have been delete");
        }
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        return JwtAuthenticationResponse
                .builder()
                .access_token(jwtTokenUtil.generateToken(new UserDetails() {
                    @Override
                    public Collection<? extends GrantedAuthority> getAuthorities() {
                        return null;
                    }

                    @Override
                    public String getPassword() {
                        return userEntity.get().getPassword();
                    }

                    @Override
                    public String getUsername() {
                        return userEntity.get().getUserName();
                    }

                    @Override
                    public boolean isAccountNonExpired() {
                        return false;
                    }

                    @Override
                    public boolean isAccountNonLocked() {
                        return false;
                    }

                    @Override
                    public boolean isCredentialsNonExpired() {
                        return false;
                    }

                    @Override
                    public boolean isEnabled() {
                        return true;
                    }
                }))
                .expires_in(JwtTokenUtil.JWT_TOKEN_VALIDITY)
                .refresh_token("")
                .build();
    }
}