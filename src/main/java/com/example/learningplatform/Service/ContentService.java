package com.example.learningplatform.Service;

import com.example.learningplatform.Api.ApiException.ApiException;
import com.example.learningplatform.Model.Course;
import com.example.learningplatform.Model.CourseItem;
import com.example.learningplatform.Model.Order;
import com.example.learningplatform.Model.Teacher;
import com.example.learningplatform.Repository.CourseItemRepository;
import com.example.learningplatform.Repository.CourseRepository;
import com.example.learningplatform.Repository.OrderRepository;
import com.example.learningplatform.Repository.TeacherRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final CourseRepository courseRepository;
    private final CourseItemRepository courseItemRepository;
    private final TeacherRepository teacherRepository;
    private final OrderRepository orderRepository;

    private final String SERVER_FILES_FOLDER = "C:/Users/isaud/IdeaProjects/System/src/main/resources/courses_files/";


    public record FileInfoRecord(MediaType mediaType, byte[] data) {
    }

    public String uploadCourseVideo(MultipartFile file, String teacherToken, Integer courseID, CourseItem item, String contentTitle) throws IOException, ApiException {

        if (file.isEmpty()) throw new ApiException("File does not exist.");


        ////// we'll make a directory for each user in our Server and store whatever we need to retrieve x file in the database, this way is way faster than storing the files in the db as BLOB.
        Teacher teacher = teacherRepository.findTeacherByToken(teacherToken);
        if (teacher == null) {
            throw new ApiException("Wrong token, please enter a correct token.");
        }

        Course course = courseRepository.findCourseById(courseID);
        if (course == null) {
            throw new ApiException("Wrong ID, please double check the course ID");
        }

        if (!Objects.equals(course.getTeacher().getId(), teacher.getId())) {
            throw new ApiException("This course does not belong to this teacher.");
        }


        String fileLocation = SERVER_FILES_FOLDER + teacher.getId() + "/" + course.getId() + "/" + item.getId() + "/" + file.getOriginalFilename();

        Files.createDirectories(Paths.get(fileLocation));

        file.transferTo(new File(fileLocation));


        ////TODO put the video duration here
        HashMap<String, String> itemContent = new HashMap<>(3) {{
            put("path", fileLocation);
            put("title", contentTitle);
            put("duration", "5:00 minutes");
        }};

        item.setCourse(course);
//        item.getContent().add(itemContent);

        courseItemRepository.save(item);

        return "Video named " + file.getOriginalFilename() + " added to section content successfully !";
    }


    public Set<CourseItem> getCourseSections(Integer courseID) throws ApiException {

        Course course = courseRepository.findCourseById(courseID);

        if (course == null) throw new ApiException("Course was not found, double check the ID.");

        Set<CourseItem> courseItems = course.getCourseItems();

        if (courseItems.isEmpty()) throw new ApiException("This course is still empty, try adding items to it.");

        return courseItems;
    }


    ///// all of these
    public byte[] downloadFileById(Integer courseID, Integer studentID, String fileLocation) throws IOException, ApiException {


        ///// TODO change this to check by course_id and student_id when the relation is done
        Order purchasesCourse = orderRepository.findOrderById(0000);

        if (purchasesCourse == null) {
            throw new ApiException("You have not purchased this course");
        }

        return Files.readAllBytes(new File(fileLocation).toPath());
    }
}
