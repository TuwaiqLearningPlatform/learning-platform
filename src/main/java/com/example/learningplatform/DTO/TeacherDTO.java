package com.example.learningplatform.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherDTO {

    @NotEmpty(message = "the name field is required.")
    private String name;

    @NotEmpty(message = "the username field is required.")
    private String username;

    @NotEmpty(message = "the password field is required.")
    @Pattern(message = "the password must contain at least eight characters, at least one number and both lower and uppercase letters and special characters", regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String password;

    @NotEmpty(message = "the email field is required.")
    @Email(message = "the email is invalid.")
    private String email;

    @NotNull(message = "the years of experience field is required.")
    @PositiveOrZero(message = "the years of experience must be either 0 or positive number.")
    private Integer yearsOfExperience;

}
