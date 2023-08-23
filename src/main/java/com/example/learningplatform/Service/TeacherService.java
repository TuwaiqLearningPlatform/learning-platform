package com.example.learningplatform.Service;

import com.example.learningplatform.Api.ApiException.ApiException;
import com.example.learningplatform.DTO.TeacherDTO;
import com.example.learningplatform.Model.Teacher;
import com.example.learningplatform.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;


    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }



    public Teacher findById(Integer id) throws ApiException {
        Teacher teacher = teacherRepository.findTeacherById(id);

        if(teacher == null) {
            throw new ApiException("teacher not found.");
        }

        return teacher;
    }

    public Teacher addTeacher(TeacherDTO teacherDTO) throws ApiException {
        Teacher teacher = setTeacherAttributes((new Teacher()), teacherDTO);

        uniqueChecks(teacher);


        teacherRepository.save(teacher);



        return teacher;
    }

    public Teacher updateTeacher(Integer id, TeacherDTO teacherDTO) throws ApiException {
        Teacher saved_teacher = findById(id);
        Teacher teacher = setTeacherAttributes(saved_teacher, teacherDTO);

        // ensure not update the username, email

        teacher.setEmail(saved_teacher.getEmail());
        teacher.setUsername(saved_teacher.getUsername());

        // any relation will go here.

        teacherRepository.save(teacher);

        return teacher;
    }

    public Teacher deleteById(Integer id) throws ApiException {
        Teacher teacher = findById(id);

        teacherRepository.deleteById(id);

        return teacher;
    }

    // END CRUD

    // search for teacher by name
    public List<Teacher> searchForTeachersByName(String name) throws ApiException {
        if(name == null) {
            throw new ApiException("the name can not be empty.");
        }

        List<Teacher> teachers = teacherRepository.lookForTeachersByName(name);

        if(teachers.isEmpty()) {
            throw new ApiException("no teachers found with your query ("+ name +") .");
        }

        return teachers;
    }


    private Teacher setTeacherAttributes(Teacher teacher, TeacherDTO teacherDTO) {
        teacher.setName(teacherDTO.getName());
        teacher.setUsername(teacherDTO.getUsername());
        teacher.setPassword(teacherDTO.getPassword());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setYearsOfExperience(teacherDTO.getYearsOfExperience());

        return teacher;
    }

    private void uniqueChecks(Teacher teacher) throws ApiException {
        if(teacherRepository.checkEmail(teacher.getEmail()) != null) {
            throw new ApiException("the email is used.");
        }

        if(teacherRepository.checkUsername(teacher.getUsername()) != null) {
            throw new ApiException("the username is used.");
        }
    }

}
