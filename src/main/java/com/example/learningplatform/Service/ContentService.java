package com.example.learningplatform.Service;

import com.example.learningplatform.Api.ApiException.ApiException;
import com.example.learningplatform.Model.*;
import com.example.learningplatform.Repository.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
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
    private final StudentRepository studentRepository;

    private final String SERVER_FILES_FOLDER = "/home/alharbi/IdeaProjects/learning-platform/src/main/resources/courses_files/";
//    private final String SERVER_FILES_FOLDER = "C:/Users/isaud/IdeaProjects/System/src/main/resources/courses_files/";

//    public record FileInfoRecord(MediaType mediaType, byte[] data) {
//    }

    public String uploadCourseVideo(MultipartFile file, String teacherToken, Integer courseID, Integer courseItemID, String contentTitle) throws IOException, ApiException {

        if (file.isEmpty()) {
            throw new ApiException("File does not exist.");
        } else if (contentTitle == null) {
            throw new ApiException("Title cannot be null");
        }

        ////// we'll make a directory for each user in our Server and store whatever we need to retrieve x file in the database, this way is way faster than storing the files in the db as BLOB.
        Teacher teacher = teacherRepository.findTeacherByToken(teacherToken);
        if (teacher == null) {
            throw new ApiException("Wrong token, please enter a correct token.");
        }

        Course course = courseRepository.findCourseById(courseID);
        if (course == null) {
            throw new ApiException("Wrong course ID , please double check the ID");
        }

        CourseItem item = courseItemRepository.findCourseItemById(courseItemID);
        if (item == null) {
            throw new ApiException("Wrong course item ID, please double check the ID");
        }

        if (!Objects.equals(course.getTeacher().getId(), teacher.getId())) {
            throw new ApiException("This course does not belong to this teacher.");
        }


        String fileLocation = SERVER_FILES_FOLDER + "teacher_" + teacher.getId() + "/course_" + course.getId() + "/item_" + item.getId() + "/" + file.getOriginalFilename();

        Files.createDirectories(Paths.get(fileLocation));

        file.transferTo(new File(fileLocation));


        ////TODO put the video duration here
        HashMap<String, String> itemContent = new HashMap<>(3) {{

            put("path", fileLocation);
            put("title", contentTitle);
            put("duration", "5:00 minutes");
        }};
        ///// TODO calculate duration.

        item.setCourse(course);

        int currentSize = item.getContent().size();
        item.getContent().put(currentSize == 0 ? 1 : currentSize + 1, itemContent);

        courseItemRepository.save(item);

        return "Video named " + file.getOriginalFilename() + " added to section content successfully !";
    }


    public void deleteContent(String teacherToken, Integer itemID, Integer contentID) {

        Teacher teacher = teacherRepository.findTeacherByToken(teacherToken);

        if (teacher == null) {
            throw new ApiException("Teacher token is incorrect");
        }

        CourseItem itemContentToDelete = courseItemRepository.findCourseItemById(itemID);

        if (itemContentToDelete == null) {
            throw new ApiException("Could not find course item");
        }

        if (!Objects.equals(itemContentToDelete.getCourse().getTeacher().getId(), teacher.getId())) {
            throw new ApiException("This item does not belong to the teacher");
        }


        if (!itemContentToDelete.getContent().containsKey(contentID)) {
            throw new ApiException("Wrong content ID, double check the ID.");
        }

        itemContentToDelete.getContent().remove(contentID);

        courseItemRepository.save(itemContentToDelete);

    }

    public byte[] getCourseItemVideo(Integer courseID, String studentToken, String fileLocation) throws IOException, ApiException {

        Student student = studentRepository.findStudentByToken(studentToken);

        Order purchasedCourse = orderRepository.findOrderByStudentIdAndCourseId(student.getId(), courseID);

        if (purchasedCourse == null) {
            throw new ApiException("You have not purchased this course");
        }

        System.out.println(fileLocation);

        return Files.readAllBytes(new File(fileLocation).toPath());
    }
}
