package com.example.learningplatform.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "the name field is required.")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "the username field is required.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // don't show the usernames to others in response.
    @Column(nullable = false, unique = true)
    private String username;

    @NotEmpty(message = "the password field is required.")
    @Pattern(message = "the password must contain at least eight characters, at least one number and both lower and uppercase letters and special characters", regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // don't show the password to others in response.
    @Column(nullable = false)
    private String password;


    @NotEmpty(message = "the email field is required.")
    @Email(message = "the email is invalid.")
    @Column(nullable = false, unique = true)
    private String email;


    // the following will be managed by the application
    private Integer revenue = 0;

    // the following will be managed by the application
    private Double avgRate = 0.0;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP) // convert from database timestamp type to java.time.LocalDateTime
    @Column(columnDefinition = "timestamp not null default CURRENT_TIMESTAMP") // ensure default
    private LocalDateTime joinDate;


    @NotNull(message = "the years of experience field is required.")
    @PositiveOrZero(message = "the years of experience must be either 0 or positive number.")
    @Column(nullable = false)
    private Integer yearsOfExperience;

    private String token;


    // Relations


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private Set<Course> courses;
}
