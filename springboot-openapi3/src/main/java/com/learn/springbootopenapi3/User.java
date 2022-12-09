package com.learn.springbootopenapi3;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "User UUID in  the database")
    @JsonProperty("id")
    private Long id;

    private @NotBlank
    @Size(min = 0, max = 20) String firstName;
    private String lastName;

    private @NotBlank @Size(min = 0, max = 50) String email;
}