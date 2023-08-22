package com.example.learningplatform.Controller;

import com.example.learningplatform.Service.CourseItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses/{id}/items")
@RequiredArgsConstructor
public class CourseItemsController {

    private final CourseItemService courseItemService;

}
