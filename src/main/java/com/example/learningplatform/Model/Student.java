package com.example.learningplatform.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "the name field is required.")
    @Column(nullable = false)
    private String name;


    @NotNull(message = "the balance field is required.")
    @PositiveOrZero(message = "the balance must be either 0 or positive number.")
    @Column(nullable = false)
    private Integer balance;


    @NotEmpty(message = "the email field is required.")
    @Email(message = "the email is invalid.")
    @Column(nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "the username field is required.")
    @Column(nullable = false, unique = true)
    private String username;


    @NotEmpty(message = "the password field is required.")
    @Pattern(message = "the password must contain at least eight characters, at least one number and both lower and uppercase letters and special characters", regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    @Column(nullable = false)
    private String password;


    @Column
    private String token;


    // Relations

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    @JsonIgnore // we don't want to show the trackings they should be shown by course id
    private Set<CourseTracking> courseTrackings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Set<Certificate> certificates;

}
