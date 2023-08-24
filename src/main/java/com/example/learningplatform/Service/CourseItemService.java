package com.example.learningplatform.Service;

import com.example.learningplatform.Api.ApiException.ApiException;
import com.example.learningplatform.DTO.CourseItemDTO;
import com.example.learningplatform.Model.Course;
import com.example.learningplatform.Model.CourseItem;
import com.example.learningplatform.Model.Teacher;
import com.example.learningplatform.Repository.CourseItemRepository;
import com.example.learningplatform.Repository.CourseRepository;
import com.example.learningplatform.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class CourseItemService {

    private final CourseRepository courseRepository;
    private final CourseItemRepository courseItemRepository;
    private final TeacherRepository teacherRepository;


    public void addCourseItem(String teacherToken, CourseItemDTO newItem, Integer courseID) {

        Teacher teacher = teacherRepository.findTeacherByToken(teacherToken);

        if (teacher == null) {
            throw new ApiException("Teacher token is incorrect");
        }

        CourseItem item = setCourseItemAttributes(new CourseItem(), newItem);

        Course courseForItem = courseRepository.findCourseById(courseID);
        if (courseForItem == null) {
            throw new ApiException("Course ID is incorrect");
        }

        ///// Assignment

        item.setCourse(courseForItem);

        courseItemRepository.save(item);

//
        courseForItem.getCourseItems().add(item);

        courseRepository.save(courseForItem);


    }

    public void deleteCourseItem(String teacherToken, Integer courseItemID) {

        ///// TODO delete folder


        Teacher teacher = teacherRepository.findTeacherByToken(teacherToken);

        if (teacher == null) {
            throw new ApiException("Teacher token is incorrect");
        }

        CourseItem item = courseItemRepository.findCourseItemById(courseItemID);

        if (item == null) {
            throw new ApiException("Could not find course item");
        }

        if (!Objects.equals(item.getCourse().getTeacher().getId(), teacher.getId())) {
            throw new ApiException("This item does not belong to the teacher");
        }

        courseItemRepository.delete(item);

    }

    public CourseItem setCourseItemAttributes(CourseItem item, CourseItemDTO itemDTO) {

        item.setTitle(itemDTO.getTitle());

        HashMap<Integer, HashMap<String, String>> methodInput = new HashMap<>();

        item.setContent(methodInput);

        return item;

    }

    public Set<CourseItem> getCourseSections(Integer courseID) throws ApiException {

        Course course = courseRepository.findCourseById(courseID);

        if (course == null) {
            throw new ApiException("Course was not found, double check the ID.");
        }

        Set<CourseItem> courseItems = course.getCourseItems();

        if (courseItems.isEmpty()) {
            throw new ApiException("This course is still empty, try adding items to it.");
        }

        return courseItems;
    }

}
