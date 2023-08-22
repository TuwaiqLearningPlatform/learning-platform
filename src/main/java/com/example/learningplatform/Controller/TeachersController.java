package com.example.learningplatform.Controller;


import com.example.learningplatform.Service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeachersController {

    private final TeacherService teacherService;



}
