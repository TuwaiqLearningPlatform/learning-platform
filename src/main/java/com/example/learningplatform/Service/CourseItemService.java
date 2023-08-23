package com.example.learningplatform.Service;

import com.example.learningplatform.Api.ApiException.ApiException;
import com.example.learningplatform.Model.Course;
import com.example.learningplatform.Model.CourseItem;
import com.example.learningplatform.Model.Teacher;
import com.example.learningplatform.Repository.CourseItemRepository;
import com.example.learningplatform.Repository.CourseRepository;
import com.example.learningplatform.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CourseItemService {

    private final CourseItemRepository courseItemRepository;

}
