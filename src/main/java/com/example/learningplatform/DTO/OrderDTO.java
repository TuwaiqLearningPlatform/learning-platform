package com.example.learningplatform.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDTO {


    @NotNull(message = "the teacher id field is required.")
    @Positive(message = "the teacher id must be positive number.")
    private Integer teacherId;


    @NotNull(message = "the student id field is required.")
    @Positive(message = "the student id must be positive number.")
    private Integer studentId;


}
