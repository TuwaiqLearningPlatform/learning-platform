package com.example.learningplatform.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseItemDTO {

    @NotEmpty(message = "the title field is required.")
    private String title;

}
