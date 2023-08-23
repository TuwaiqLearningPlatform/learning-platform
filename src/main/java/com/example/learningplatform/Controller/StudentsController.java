package com.example.learningplatform.Controller;


import com.example.learningplatform.Api.ApiResponse.ApiResponse;
import com.example.learningplatform.Api.ApiResponse.ApiResponseWithMessage;
import com.example.learningplatform.DTO.StudentDTO;
import com.example.learningplatform.Model.Student;
import com.example.learningplatform.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentsController {

    private final StudentService studentService;


    @GetMapping("/get")
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }
    @GetMapping("/search/id/{id}")
    public ResponseEntity<Student> findById(Integer id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponseWithMessage<Student>> addStudent(@RequestBody @Valid StudentDTO studentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body((new ApiResponseWithMessage<>("the student have been created.", studentService.addStudent(studentDTO))));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseWithMessage<Student>> updateStudent(@PathVariable Integer id, @RequestBody @Valid StudentDTO studentDTO) {
        return ResponseEntity.ok((new ApiResponseWithMessage<>("the student have been updated.", studentService.updateStudent(id, studentDTO))));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseWithMessage<Student>> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok((new ApiResponseWithMessage<>("the student have been deleted.", studentService.deleteById(id))));
    }
}
