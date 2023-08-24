package com.example.learningplatform.Controller;


import com.example.learningplatform.Api.ApiResponse.ApiResponse;
import com.example.learningplatform.Api.ApiResponse.ApiResponseWithMessage;
import com.example.learningplatform.DTO.TeacherDTO;
import com.example.learningplatform.Model.Teacher;
import com.example.learningplatform.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeachersController {

    private final TeacherService teacherService;


    // START CRUD

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Teacher>>> findAll() {
        return ResponseEntity.ok(new ApiResponse<>(teacherService.findAll()));
    }

    @PostMapping("/get/revenue/{teacherToken}")
    public ResponseEntity<?> GetRevenue(@PathVariable String teacherToken) {

        return ResponseEntity.ok(new ApiResponse<>("Your total revenue is: " + teacherService.getRevenue(teacherToken)));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponseWithMessage<Teacher>> addTeacher(@RequestBody @Valid TeacherDTO teacherDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body((new ApiResponseWithMessage<>("the teacher have been created.", teacherService.addTeacher(teacherDTO))));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseWithMessage<Teacher>> updateTeacher(@PathVariable Integer id, @RequestBody @Valid TeacherDTO teacherDTO) {
        return ResponseEntity.ok((new ApiResponseWithMessage<>("the teacher have been updated.", teacherService.updateTeacher(id, teacherDTO))));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseWithMessage<Teacher>> deleteTeacher(@PathVariable Integer id) {
        return ResponseEntity.ok((new ApiResponseWithMessage<>("the teacher have been deleted.", teacherService.deleteById(id))));
    }

    // END CRUD

    // Search by name

    @GetMapping("/search/name/{name}")
    public ResponseEntity<ApiResponse<List<Teacher>>> searchByName(@PathVariable String name) {
        return ResponseEntity.ok((new ApiResponse<>(teacherService.searchForTeachersByName(name))));
    }

    @GetMapping("/search/id/{id}")
    public ResponseEntity<ApiResponse<Teacher>> searchByName(@PathVariable Integer id) {
        return ResponseEntity.ok((new ApiResponse<>(teacherService.findById(id))));
    }

}
