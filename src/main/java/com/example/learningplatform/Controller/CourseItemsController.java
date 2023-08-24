package com.example.learningplatform.Controller;

import com.example.learningplatform.DTO.CourseItemDTO;
import com.example.learningplatform.Service.ContentService;
import com.example.learningplatform.Service.CourseItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/course-items")
@RequiredArgsConstructor
public class CourseItemsController {

    private final CourseItemService courseItemService;
    private final ContentService contentService;


    @GetMapping("/{courseID}/items")
    public ResponseEntity<?> getCourseItems(@PathVariable Integer courseID) {

        return ResponseEntity.ok(courseItemService.getCourseSections(courseID));
    }


    @GetMapping("/get-video/{courseID}/{studentToken}")
    public ResponseEntity<?> getCourseItemVideo(@PathVariable Integer courseID, @PathVariable String studentToken, @RequestParam("filePath") String filePath) throws IOException {

        return ResponseEntity.ok(contentService.getCourseItemVideo(courseID, studentToken, filePath));

    }

    @PostMapping("/add/{teacherToken}/{courseID}")
    public ResponseEntity<?> addCourseItem(@RequestBody @Valid CourseItemDTO item, @PathVariable String teacherToken, @PathVariable Integer courseID) {

        courseItemService.addCourseItem(teacherToken, item, courseID);
        return ResponseEntity.ok("Course item added successfully !");
    }

    @DeleteMapping("/delete/{teacherToken}/{courseItemID}")
    public ResponseEntity<?> deleteCourseItem(@PathVariable String teacherToken, @PathVariable Integer courseItemID) {

        courseItemService.deleteCourseItem(teacherToken, courseItemID);
        return ResponseEntity.ok("Course item deleted successfully !");
    }


    ///// this is how the teacher adds course videos.
    @PostMapping("/add-content/{teacherToken}/{courseID}/{courseItemID}")
    public ResponseEntity<?> addCourseContent(@RequestParam("file") MultipartFile file, @PathVariable String teacherToken, @PathVariable Integer courseID, @PathVariable Integer courseItemID, @RequestParam("contentTitle") String contentTitle) throws IOException {

        return ResponseEntity.ok(contentService.uploadCourseVideo(file, teacherToken, courseID, courseItemID, contentTitle));
    }

    @DeleteMapping("/delete/{teacherToken}/{courseItemID}/{contentID}")
    public ResponseEntity<?> deleteCourseItemContent(@PathVariable String teacherToken, @PathVariable Integer courseItemID, @PathVariable Integer contentID) {

        contentService.deleteContent(teacherToken, courseItemID, contentID);
        return ResponseEntity.ok("Course item added successfully !");
    }
}
