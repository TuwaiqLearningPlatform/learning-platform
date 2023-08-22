package com.example.learningplatform.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


}
