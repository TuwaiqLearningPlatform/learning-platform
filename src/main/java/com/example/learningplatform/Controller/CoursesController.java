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
    public ResponseEntity<ApiResponse<List<Course>>> findAll() {
        return ResponseEntity.ok((new ApiResponse<>(courseService.findAll())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> findById(@PathVariable Integer id) {
        return ResponseEntity.ok((new ApiResponse<>(courseService.findById(id))));
    }

    @PostMapping("/add/{token}")
    public ResponseEntity<ApiResponseWithMessage<Course>> addCourse(@PathVariable String token, @RequestBody @Valid CourseDTO courseDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body((new ApiResponseWithMessage<>("the course have been created.", courseService.addCourse(token, courseDTO))));
    }

    @PutMapping("/update/{token}/{courseId}")
    public ResponseEntity<ApiResponseWithMessage<Course>> updateCourse(@PathVariable String token, @PathVariable Integer courseId, @RequestBody @Valid CourseDTO courseDTO) {
        return ResponseEntity.ok((new ApiResponseWithMessage<>("the course have been updated.", courseService.updateCourse(token, courseId, courseDTO))));
    }

    @DeleteMapping("/delete/{token}/{courseId}")
    public ResponseEntity<ApiResponseWithMessage<Course>> deleteCourse(@PathVariable Integer courseId, @PathVariable String token) {
        return ResponseEntity.ok((new ApiResponseWithMessage<>("the course have been deleted.", courseService.deleteById(courseId, token))));
    }
}
