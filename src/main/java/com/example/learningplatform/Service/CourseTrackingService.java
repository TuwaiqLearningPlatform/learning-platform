package com.example.learningplatform.Service;

import com.example.learningplatform.Model.CourseTracking;
import com.example.learningplatform.Repository.CourseTrackingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseTrackingService {


    private final CourseTrackingRepository courseTrackingRepository;



}
