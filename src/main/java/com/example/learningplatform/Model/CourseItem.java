package com.example.learningplatform.Model;

import com.example.learningplatform.Converter.ListHashMapConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "course_items")
public class CourseItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "the title field is required.")
    @Column(nullable = false)
    private String title;

    // read more: https://www.baeldung.com/hibernate-persist-json-object
    @Convert(converter = ListHashMapConverter.class)
    @Column(columnDefinition = "TEXT") // use text to ensure not to run into issues when there's a lot of content
    private HashMap<Integer, HashMap<String, String>> content;


    // Relations

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @JsonIgnore
    private Course course;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "courseItem")
    @PrimaryKeyJoinColumn
    private CourseTracking courseTracking;

}
