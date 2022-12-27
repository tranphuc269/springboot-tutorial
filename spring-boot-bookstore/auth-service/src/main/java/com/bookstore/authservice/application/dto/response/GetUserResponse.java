package com.bookstore.authservice.application.dto.response;

import java.util.Set;

import com.bookstore.authservice.infrastructure.data.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserResponse {

  private String userId;
  private String userName;
  private String firstName;
  private String lastName;
  private String email;
  private Set<RoleEntity> roles;

}