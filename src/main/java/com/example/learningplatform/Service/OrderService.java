package com.example.learningplatform.Service;

import com.example.learningplatform.Api.ApiException.ApiException;
import com.example.learningplatform.DTO.CourseDTO;
import com.example.learningplatform.DTO.OrderDTO;
import com.example.learningplatform.Model.*;
import com.example.learningplatform.Repository.CourseRepository;
import com.example.learningplatform.Repository.OrderRepository;
import com.example.learningplatform.Repository.StudentRepository;
import com.example.learningplatform.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;


    public String buyCourse(Integer courseID, String studentToken) {

        Course courseToBuy = courseRepository.findCourseById(courseID);

        if (courseToBuy == null) {

            throw new ApiException("Course was not found");
        }

        Teacher courseTeacher = courseToBuy.getTeacher();

        Student student = studentRepository.findStudentByToken(studentToken);

        if (student == null) {

            throw new ApiException("Wrong student token");
        }

        Integer studentBalance = student.getBalance();
        Integer teacherRevenue = courseTeacher.getRevenue();
        Integer coursePrice = courseToBuy.getPrice();

        if (studentBalance < coursePrice) {
            throw new ApiException("Not enough balance to buy this course");
        }

        student.setBalance(studentBalance - coursePrice);
        courseTeacher.setRevenue(teacherRevenue + coursePrice);


        Order order = new Order();
        order.setCourse(courseToBuy);
        order.setTeacher(courseTeacher);
        order.setStudent(student);

        orderRepository.save(order);
        teacherRepository.save(courseTeacher);
        studentRepository.save(student);

        return courseToBuy.getTitle().concat(" was bought successfully !");

    }
}
