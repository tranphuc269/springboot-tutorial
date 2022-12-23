package com.bookstore.authservice.application.dto.request;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleRequest{

    @NotBlank
    private String roleName;
    @NotBlank
    private String roleDescription;

}