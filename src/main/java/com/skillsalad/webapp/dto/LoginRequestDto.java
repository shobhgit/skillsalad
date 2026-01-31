package com.skillsalad.webapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    public String getEmail() {

        return email;
    }

    public String getPassword() {
        return password;
    }
}
