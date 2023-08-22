package com.example.learningplatform.Controller;


import com.example.learningplatform.Service.CourseTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses/{id}/trackings")
@RequiredArgsConstructor
public class CourseTrackingsController {

    private final CourseTrackingService courseTrackingService;


}
