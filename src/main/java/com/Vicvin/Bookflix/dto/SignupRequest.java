package com.Vicvin.Bookflix.dto;

import com.Vicvin.Bookflix.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class SignupRequest {

    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // default if not provided
}
