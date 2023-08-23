package com.example.learningplatform.Repository;

import com.example.learningplatform.Model.Student;
import com.example.learningplatform.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findStudentById(Integer id);

    Student findStudentByToken(String token);

}
