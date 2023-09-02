package com.example.learningplatform.Controller;


import com.example.learningplatform.Api.ApiResponse.ApiResponse;
import com.example.learningplatform.Api.ApiResponse.ApiResponseWithMessage;
import com.example.learningplatform.DTO.CourseDTO;
import com.example.learningplatform.Model.Course;
import com.example.learningplatform.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CoursesController {

    private final CourseService courseService;


    @GetMapping("/get")
    public ResponseEntity<List<Course>> findAll() {
        return ResponseEntity.ok((courseService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @PostMapping("/add/{token}")
    public ResponseEntity<Course> addCourse(@PathVariable String token, @RequestBody @Valid CourseDTO courseDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourse(token, courseDTO));
    }

    @PutMapping("/update/{token}/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable String token, @PathVariable Integer courseId, @RequestBody @Valid CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.updateCourse(token, courseId, courseDTO));
    }

    @DeleteMapping("/delete/{token}/{courseId}")
    public ResponseEntity<ApiResponseWithMessage<Course>> deleteCourse(@PathVariable Integer courseId, @PathVariable String token) {
        return ResponseEntity.ok((new ApiResponseWithMessage<>("the course have been deleted.", courseService.deleteById(courseId, token))));
    }
}
