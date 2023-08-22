package com.example.learningplatform.Repository;

import com.example.learningplatform.Model.CourseTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTrackingRepository extends JpaRepository<CourseTracking, Integer> {

    CourseTracking findCourseTrackingById(Integer id);

}
