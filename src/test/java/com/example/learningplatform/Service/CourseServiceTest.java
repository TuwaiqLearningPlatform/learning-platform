package com.example.learningplatform.Service;

import com.example.learningplatform.DTO.CourseDTO;
import com.example.learningplatform.Model.Course;
import com.example.learningplatform.Model.Student;
import com.example.learningplatform.Model.Teacher;
import com.example.learningplatform.Repository.CourseRepository;
import com.example.learningplatform.Repository.TeacherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @InjectMocks
    CourseService courseService;

    @Mock
    CourseRepository courseRepository;

    @Mock
    TeacherRepository teacherRepository;


    Teacher teacher;

    Course course1;
    Course course2;
    Course course3;

    List<Course> courses;

    Set<Course> courseSet = new HashSet<>();

    @BeforeEach
    void setUp() {
        teacher = new Teacher(null, "Saud", "Saud123", "abc12345", "saud@gmail.com", "ksjldghvbloaahweoif3h03q8ruyf", courseSet);

        course1 = new Course("Python", 100, "Intro to python", true, teacher);
        course2 = new Course("Java", 100, "Intro to java", true, teacher);
        course3 = new Course("Rust", 999, "Intro to rust", true, teacher);

        courses = new ArrayList<>();

        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
    }

    @Test
    void findAll() {

        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> courseList = courseService.findAll();
        Assertions.assertEquals(courseList, courses);

        verify(courseRepository, times(1)).findAll();

    }

    @Test
    void findById() {
        when(courseRepository.findCourseById(course1.getId())).thenReturn(course1);

        Course courseCheck = courseService.findById(course1.getId());

        Assertions.assertEquals(courseCheck, course1);

        verify(courseRepository, times(1)).findCourseById(course1.getId());
    }

    @Test
    void addCourse() {
        when(teacherRepository.findTeacherByToken(teacher.getToken())).thenReturn(teacher);

        Course newCourse = courseService.addCourse(teacher.getToken(), new CourseDTO(course1.getTitle(), course1.getPrice(), course1.getDescription(), ""));

        Assertions.assertEquals(newCourse.getTitle(), course1.getTitle());
        System.out.println("1: " + newCourse.getTitle() + "\n2: " + course1.getTitle());

        Assertions.assertEquals(newCourse.getPrice(), course1.getPrice());
        System.out.println("1: " + newCourse.getPrice() + "\n2: " + course1.getPrice());

        Assertions.assertEquals(newCourse.getDescription(), course1.getDescription());
        System.out.println("1: " + newCourse.getDescription() + "\n2: " + course1.getDescription());


        verify(teacherRepository, times(1)).findTeacherByToken(teacher.getToken());
        verify(teacherRepository, times(1)).save(teacher);
        verify(courseRepository, times(1)).save(newCourse);

    }

    @Test
    void updateCourse() {
        when(teacherRepository.findTeacherByToken(teacher.getToken())).thenReturn(teacher);
        when(courseRepository.findCourseById(course1.getId())).thenReturn(course1);

        Course updateCourse = courseService.updateCourse(teacher.getToken(), course2.getId(), new CourseDTO(course1.getTitle(), course1.getPrice(), course1.getDescription(), ""));

        Assertions.assertEquals(updateCourse.getTitle(), course1.getTitle());
        System.out.println("1: " + updateCourse.getTitle() + "\n2: " + course1.getTitle());

        Assertions.assertEquals(updateCourse.getPrice(), course1.getPrice());
        System.out.println("1: " + updateCourse.getPrice() + "\n2: " + course1.getPrice());

        Assertions.assertEquals(updateCourse.getDescription(), course1.getDescription());
        System.out.println("1: " + updateCourse.getDescription() + "\n2: " + course1.getDescription());


        verify(teacherRepository, times(1)).findTeacherByToken(teacher.getToken());
        verify(courseRepository, times(1)).findCourseById(course1.getId());
        verify(courseRepository, times(1)).save(updateCourse);

    }

    @Test
    void deleteById() {
        when(teacherRepository.findTeacherByToken(teacher.getToken())).thenReturn(teacher);
        when(courseRepository.findCourseById(course1.getId())).thenReturn(course1);

        Course deleteCourse = courseService.deleteById(course1.getId(), teacher.getToken());

        Assertions.assertEquals(deleteCourse, course1);


        verify(teacherRepository, times(1)).findTeacherByToken(teacher.getToken());
        verify(courseRepository, times(1)).findCourseById(course1.getId());
        verify(courseRepository, times(1)).deleteById(course1.getId());


    }
}