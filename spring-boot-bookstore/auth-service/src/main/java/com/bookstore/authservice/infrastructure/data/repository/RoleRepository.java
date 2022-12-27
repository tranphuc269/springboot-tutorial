package com.bookstore.authservice.infrastructure.data.repository;

import com.bookstore.authservice.infrastructure.data.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

  Optional<RoleEntity> findByRoleName(String name);

  Boolean existsByRoleName(String roleName);

  @Override
  List<RoleEntity> findAll();
}