package com.example.learningplatform.Controller;

import com.example.learningplatform.Api.ApiResponse.ApiResponse;
import com.example.learningplatform.Model.Course;
import com.example.learningplatform.Model.Student;
import com.example.learningplatform.Model.Teacher;
import com.example.learningplatform.Service.CourseService;
import com.example.learningplatform.Service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.Matchers;


import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CoursesController.class)
class CoursesControllerTest {


    @MockBean
    CourseService courseService;

    @Autowired
    MockMvc mockMvc;

    Course c1, c2, c3;
    Teacher teacher;
    ApiResponse apiResponse;

    List<Course> courses, courseList;
    Set<Course> courseSet = new HashSet<>();


    @BeforeEach
    void setUp() {
        teacher = new Teacher(null, "Saud", "Saud123", "abc12345", "saud@gmail.com", "ksjldghvbloaahweoif3h03q8ruyf", courseSet);

        c1 = new Course("Python", 100, "Intro to python", true, teacher);
        c2 = new Course("Java", 100, "Intro to java", true, teacher);
        c3 = new Course("Rust", 999, "Intro to rust", true, teacher);

        courses = Arrays.asList(c1);
        courseList = Arrays.asList(c2);


    }

    @Test
    void findAll() throws Exception {

        Mockito.when(courseService.findAll()).thenReturn(courses);
        mockMvc.perform(get("/api/v1/courses/get"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(c1.getTitle()));
    }

    @Test
    void findById() throws Exception {

        mockMvc.perform(get("/api/v1/courses/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(c1)))
                .andExpect(status().isOk());

    }

    @Test
    void addCourse() throws Exception {

        mockMvc.perform(post("/api/v1/courses/add/" + teacher.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(c1)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCourse() throws Exception {
        mockMvc.perform(put("/api/v1/courses/update/" + teacher.getToken() + "/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(c1)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCourse() throws Exception {
        mockMvc.perform(delete("/api/v1/courses/delete/" + teacher.getToken() + "/" + 1))
                .andExpect(status().isOk());
    }
}