package com.example.learningplatform.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer balance;

    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false, unique = true)
    private String username;


    @Column(nullable = false)
    private String password;


    private String token = null;


    // Relations

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    @JsonIgnore // we don't want to show the trackings they should be shown by course id
    private Set<CourseTracking> courseTrackings;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Set<Certificate> certificates;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Set<Order> orders;
}
