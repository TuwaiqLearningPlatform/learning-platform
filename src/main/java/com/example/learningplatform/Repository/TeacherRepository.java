package com.example.learningplatform.Repository;

import com.example.learningplatform.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Teacher findTeacherById(Integer id);
    Teacher findTeacherByToken(String token);

    Teacher findTeacherByUsernameAndPassword(String username, String password);

    @Query("SELECT t FROM teachers t WHERE t.name LIKE '%' || ?1 || '%'")
    List<Teacher> lookForTeachersByName(String name);

    @Query("SELECT t FROM teachers  t WHERE t.email = ?1")
    Teacher checkEmail(String email);


    @Query("SELECT t FROM teachers  t WHERE t.username = ?1")
    Teacher checkUsername(String username);

}
