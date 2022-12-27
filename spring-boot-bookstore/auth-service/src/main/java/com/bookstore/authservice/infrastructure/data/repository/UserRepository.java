package com.bookstore.authservice.infrastructure.data.repository;

import com.bookstore.authservice.infrastructure.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUserName(String username);

    Optional<UserEntity> findByUserNameOrEmail(String uName, String eMail);

    Optional<UserEntity> findByUserId(String userId);

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);
}
