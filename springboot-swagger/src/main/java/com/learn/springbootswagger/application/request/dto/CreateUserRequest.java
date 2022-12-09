package com.learn.springbootswagger.application.request.dto;


import lombok.*;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Service
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
}
