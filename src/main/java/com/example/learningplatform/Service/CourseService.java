package com.example.learningplatform.Service;

import com.example.learningplatform.Api.ApiException.ApiException;
import com.example.learningplatform.DTO.CourseDTO;
import com.example.learningplatform.Model.Course;
import com.example.learningplatform.Model.Teacher;
import com.example.learningplatform.Repository.CourseRepository;
import com.example.learningplatform.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;



    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Integer id) {
        return courseRepository.findCourseById(id);
    }

    public Course addCourse(String token, CourseDTO courseDTO) {
        // TODO: add authentication @Blank-13

        Teacher teacher = teacherRepository.findTeacherByToken(token);

        if(teacher == null) {
            throw new ApiException("teacher not found.");
        }

        Course course = setCourseAttributes((new Course()), courseDTO);

        // setup relations
        course.setTeacher(teacher);
        teacher.getCourses().add(course);


        courseRepository.save(course);
        teacherRepository.save(teacher);

        return course;
    }

    public Course updateCourse(String token, Integer courseId, CourseDTO courseDTO) throws ApiException {
        Teacher teacher = teacherRepository.findTeacherByToken(token);

        if(teacher == null) {
            throw new ApiException("teacher not found.");
        }

        Course saved_course = findById(courseId);

        if(!Objects.equals(saved_course.getTeacher().getId(), teacher.getId())) {
            throw new ApiException("You don't have permissions to edit this file.");
        }

        Course course = setCourseAttributes(saved_course, courseDTO);

        courseRepository.save(course);

        return course;
    }


    public Course deleteById(Integer id, String token) {
        // TODO: add authentication @Blank-13

        Teacher teacher = teacherRepository.findTeacherByToken(token);

        if(teacher == null) {
            throw new ApiException("teacher not found.");
        }



        Course course = findById(id);

        if(!Objects.equals(course.getTeacher().getId(), teacher.getId())) {
            throw new ApiException("You don't have permissions to edit this file.");
        }

        courseRepository.deleteById(id);

        return course;
    }


    private Course setCourseAttributes(Course course, CourseDTO courseDTO) {
        course.setTitle(course.getTitle());
        course.setCourseImg(courseDTO.getCourseImg());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());

        return course;
    }

}
