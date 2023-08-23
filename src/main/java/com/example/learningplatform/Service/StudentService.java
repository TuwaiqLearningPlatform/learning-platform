package com.example.learningplatform.Service;

import com.example.learningplatform.Api.ApiException.ApiException;
import com.example.learningplatform.DTO.StudentDTO;
import com.example.learningplatform.Model.Student;
import com.example.learningplatform.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {


    private final StudentRepository studentRepository;


    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Integer id) {
        Student student = studentRepository.findStudentById(id);

        if (student == null) {
            throw new ApiException("student not found");
        }

        return student;
    }

    public Student addStudent(StudentDTO studentDTO) {
        Student student = setStudentAttributes((new Student()), studentDTO);

        uniqueChecks(student);

        studentRepository.save(student);

        return student;
    }

    public Student updateStudent(Integer id, StudentDTO studentDTO) {
        Student saved_student = findById(id);
        String email = saved_student.getEmail();
        String username = saved_student.getUsername();

        Student student = setStudentAttributes(saved_student, studentDTO);

        student.setEmail(email);
        student.setUsername(username);

        studentRepository.save(student);

        return student;
    }

    public Student deleteById(Integer id) {
        Student student = findById(id);

        studentRepository.deleteById(id);

        return student;
    }


    private Student setStudentAttributes(Student student, StudentDTO studentDTO) {
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setPassword(studentDTO.getPassword());
        student.setUsername(studentDTO.getUsername());
        student.setBalance(studentDTO.getBalance());
        student.setToken(UUID.randomUUID().toString());

        return student;
    }

    private void uniqueChecks(Student student) {

        if (studentRepository.checkEmail(student.getEmail()) != null) {
            throw new ApiException("the email is used.");
        }

        if (studentRepository.checkUsername(student.getUsername()) != null) {
            throw new ApiException("the username is used.");
        }
    }

}
