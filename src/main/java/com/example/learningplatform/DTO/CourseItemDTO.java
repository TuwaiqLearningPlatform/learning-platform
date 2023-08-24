package com.example.learningplatform.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseItemDTO {


    @Getter
    @Setter
    @NotEmpty(message = "the title field is required.")
    private String title;

}
