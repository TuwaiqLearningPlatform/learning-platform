package com.example.learningplatform.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "certificates")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP) // convert from database timestamp type to java.time.LocalDateTime
    @Column(columnDefinition = "timestamp not null default CURRENT_TIMESTAMP") // ensure default
    private LocalDateTime createdAt;

    private String path;


    // Relations

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @JsonIgnore
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @JsonIgnore
    private Course course;




}
