package com.example.learningplatform.Service;

import com.example.learningplatform.Repository.CourseItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseItemService {

    private final CourseItemRepository courseItemRepository;


}
