package com.example.learningplatform.Service;

import com.example.learningplatform.Model.Certificate;
import com.example.learningplatform.Model.Course;
import com.example.learningplatform.Model.Student;
import com.example.learningplatform.Repository.CertificateRepository;
import com.example.learningplatform.Repository.CourseRepository;
import com.example.learningplatform.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository certificateRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


    // the path to store the certificate

    // path to certificate image file.
    private final String CERTIFICATE_PATH = "/home/alharbi/IdeaProjects/learning-platform/src/main/resources/static/certificate.png";
    private final String WRITE_TO = "/home/alharbi/IdeaProjects/learning-platform/src/main/resources/static/";


    // get certificates by student id
    public List<Certificate> findAllByStudentId(Integer id) {
        return certificateRepository.findAllByStudentId(id);
    }


    // generate certificate and store it
    public Certificate generateCertificateForStudent(Integer studentId, Integer courseId) throws IOException {
        Student student = studentRepository.findStudentById(studentId);
        Course course = courseRepository.findCourseById(courseId);

        String studentName = student.getName();
        String courseName = course.getTitle();


        // generate

        final BufferedImage image = ImageIO.read(new File(CERTIFICATE_PATH));
        Color color = new Color(1, 4, 73);
        Graphics g = image.getGraphics();
        g.setColor(color);

        Font font = new Font("Arial", Font.BOLD, 70);
        g.setFont(font);
        g.drawString(studentName, 100, 880);
        g.drawString(courseName, 100, 1324);

        g.dispose();

        String path = WRITE_TO + studentId +"_" + studentName +  "_certificate.png";
        ImageIO.write(image, "png", new File(path));


        Certificate certificate = new Certificate();
        certificate.setStudent(student);
        certificate.setCourse(course);
        certificate.setPath(path);


        certificateRepository.save(certificate);

        return certificate;
    }




}
