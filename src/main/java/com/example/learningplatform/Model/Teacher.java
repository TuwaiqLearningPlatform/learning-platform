package com.example.learningplatform.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // don't show the usernames to others in response.
    @Column(nullable = false, unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // don't show the password to others in response.
    @Column(nullable = false)
    private String password;

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


    @Column(nullable = false)
    private Integer yearsOfExperience;

    private String token = null;


    // Relations


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private Set<Course> courses;
}
