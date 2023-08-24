package com.example.learningplatform.Controller;

import com.example.learningplatform.Api.ApiResponse.ApiResponse;
import com.example.learningplatform.Api.ApiResponse.ApiResponseWithMessage;
import com.example.learningplatform.Model.Certificate;
import com.example.learningplatform.Service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/certificates")
@RequiredArgsConstructor
public class CertificatesController {

    private final CertificateService certificateService;


    @GetMapping("/{studentId}")
    public ResponseEntity<ApiResponse<List<Certificate>>> findAll(@PathVariable Integer studentId) {
        return ResponseEntity.ok((new ApiResponse<>(certificateService.findAllByStudentId(studentId))));
    }

    @PostMapping("/generate/{studentId}/{courseId}")
    public ResponseEntity<ApiResponseWithMessage<Certificate>> generate(@PathVariable Integer studentId, @PathVariable Integer courseId) throws IOException {
        return ResponseEntity.ok((new ApiResponseWithMessage<>("the certificate have been generated.", certificateService.generateCertificateForStudent(studentId, courseId))));
    }
}
