package com.example.learningplatform.Repository;

import com.example.learningplatform.Model.Student;
import com.example.learningplatform.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findStudentById(Integer id);

    Student findStudentByToken(String token);

    Student findStudentByUsernameAndPassword(String username, String password);

    @Query("SELECT s FROM students s WHERE s.email = ?1")
    Student checkEmail(String email);

    @Query("SELECT s FROM students s WHERE s.username = ?1")
    Student checkUsername(String username);

}
