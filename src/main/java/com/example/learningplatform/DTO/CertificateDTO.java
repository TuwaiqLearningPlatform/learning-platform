package com.example.learningplatform.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CertificateDTO {

    @NotNull(message = "the student id field is required.")
    @Positive(message = "the student id must be positive number.")
    private Integer studentId;


    @NotNull(message = "the course id field is required.")
    @Positive(message = "the course id must be positive number.")
    private Integer courseId;

}
