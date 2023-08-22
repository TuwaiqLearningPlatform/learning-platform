package com.example.learningplatform.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseDTO {

    @NotEmpty(message = "the title field is required.")
    private String title;

    @NotNull(message = "the price field is required.")
    @PositiveOrZero(message = "the price must be either 0 or positive number.")
    private Integer price;


    private String description; // this can be updated later.

    private String courseImg;  // this can be updated later.
}
