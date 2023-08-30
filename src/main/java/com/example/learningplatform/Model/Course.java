package com.example.learningplatform.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "the title field is required.")
    @Column(nullable = false)
    private String title;


    @NotNull(message = "the price field is required.")
    @PositiveOrZero(message = "the price must be either 0 or positive number.")
    @Column(nullable = false)
    private Integer price;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP) // convert from database timestamp type to java.time.LocalDateTime
    @Column(columnDefinition = "timestamp not null default CURRENT_TIMESTAMP") // ensure default
    private LocalDateTime createdAt;


    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP) // convert from database timestamp type to java.time.LocalDateTime
    @Column(columnDefinition = "timestamp not null default CURRENT_TIMESTAMP") // ensure default
    private LocalDateTime lastUpdate;

    @Column()
    private String description; // this can be updated later.

    @Column()
    private String courseImg; // this also can be updated later.


    // Relations

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @JsonIgnore
    private Teacher teacher;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<CourseItem> courseItems;


    //// TODO delete this later.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<Certificate> certificates;


    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "course")
    private Set<Order> orders;

    @Column(columnDefinition = "boolean not null default true ")
    private Boolean isActive = true;


    public Course(String title, Integer price, String description, Boolean isActive, Teacher teacher) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.isActive = isActive;
        this.teacher = teacher;
    }
}
