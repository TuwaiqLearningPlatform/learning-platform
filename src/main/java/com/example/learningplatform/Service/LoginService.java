package com.example.learningplatform.Service;


import com.example.learningplatform.Api.ApiException.ApiException;
import com.example.learningplatform.Model.Student;
import com.example.learningplatform.Model.Teacher;
import com.example.learningplatform.Repository.StudentRepository;
import com.example.learningplatform.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public String teacherLogin (String userName, String password){

        Teacher teacher = teacherRepository.findTeacherByUsernameAndPassword(userName, encryptPassword(password));

        if(teacher == null){
            throw new ApiException("Login failed, check username and password");
        }

        return teacher.getToken();

    }

    public String studentLogin (String userName, String password){

        Student student = studentRepository.findStudentByUsernameAndPassword(userName, encryptPassword(password));

        if(student == null){
            throw new ApiException("Login failed, check username and password");
        }

        return student.getToken();

    }


    public static String encryptPassword(String password) {

        String encryptedpassword = null;
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");

            m.update(password.getBytes());

            byte[] bytes = m.digest();

            StringBuilder s = new StringBuilder();
            for (byte aByte : bytes) {
                s.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            encryptedpassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); //// this should never be reached anyway.
        }

        return encryptedpassword;
    }
}
