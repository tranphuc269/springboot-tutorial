package com.learn.springbootswagger.infrastructure.repo.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "User model")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
}