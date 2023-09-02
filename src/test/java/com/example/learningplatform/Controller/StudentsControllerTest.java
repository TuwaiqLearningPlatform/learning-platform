package com.example.learningplatform.Controller;

import com.example.learningplatform.Api.ApiResponse.ApiResponse;
import com.example.learningplatform.Model.Student;
import com.example.learningplatform.Service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = StudentsController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class StudentsControllerTest {


    @MockBean
    StudentService studentService;

    @Autowired
    MockMvc mockMvc;

    Student st1, st2, st3;

    ApiResponse apiResponse;

    List<Student> todos,todoList;
    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void addStudent() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteById() {
    }
}