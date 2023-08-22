package com.example.learningplatform.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "course_trackings")
public class CourseTracking {


    @Id
    private Integer id;

    @Column(columnDefinition = "bit(1) default 0")
    private Boolean isCompleted = false;


    // Relations

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;


    // Relations

    @OneToOne
    @MapsId
    @JsonIgnore
    private CourseItem courseItem;

}