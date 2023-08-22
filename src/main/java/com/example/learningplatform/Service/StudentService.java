package com.example.learningplatform.Service;

import com.example.learningplatform.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {


    private final StudentRepository studentRepository;

}
