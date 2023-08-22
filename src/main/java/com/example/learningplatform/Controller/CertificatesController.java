package com.example.learningplatform.Controller;

import com.example.learningplatform.Service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/certificates")
@RequiredArgsConstructor
public class CertificatesController {

    private final CertificateService certificateService;

}
