package com.example.learningplatform.Service;

import com.example.learningplatform.Model.Teacher;
import com.example.learningplatform.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private TeacherRepository teacherRepository;


}
