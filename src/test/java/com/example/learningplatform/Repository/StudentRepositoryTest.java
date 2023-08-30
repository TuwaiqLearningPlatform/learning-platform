package com.example.learningplatform.Repository;

import com.example.learningplatform.Model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest {


    @Autowired
    StudentRepository studentRepository;

    Student student;

    @BeforeEach
    void setUp() {
        student = new Student(null, "Saud", 200, "saud@gmail.com", "saud123", "abc12345", "q274u8gihsdoljghoselui0q3ruy0", null, null);

        studentRepository.save(student);
    }

    @Test
    void findStudentById() {
        var studentCheck = studentRepository.findStudentById(student.getId());

        System.out.println("***" + student.getId());
        Assertions.assertThat(studentCheck).isEqualTo(student);

    }

    @Test
    void findStudentByToken() {

        var studentCheck = studentRepository.findStudentByToken(student.getToken());

        System.out.println("***" + student.getId());
        Assertions.assertThat(studentCheck).isEqualTo(student);
    }

    @Test
    void findStudentByUsernameAndPassword() {

        var studentCheck = studentRepository.findStudentByUsernameAndPassword(student.getUsername(), student.getPassword());

        System.out.println("***" + student.getId());

        Assertions.assertThat(studentCheck).isEqualTo(student);
    }

    @Test
    void checkEmail() {

        var studentCheck = studentRepository.checkEmail(student.getEmail());

        System.out.println("***" + student.getId());

        Assertions.assertThat(studentCheck).isEqualTo(student);
    }

    @Test
    void checkUsername() {


        var studentCheck = studentRepository.checkUsername(student.getUsername());

        System.out.println("***" + student.getId());

        Assertions.assertThat(studentCheck).isEqualTo(student);
    }
}