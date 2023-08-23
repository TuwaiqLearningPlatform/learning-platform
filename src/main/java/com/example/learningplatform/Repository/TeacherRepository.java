package com.example.learningplatform.Repository;

import com.example.learningplatform.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Teacher findTeacherById(Integer id);
    Teacher findTeacherByToken(String token);

}
