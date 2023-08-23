package com.example.learningplatform.Repository;

import com.example.learningplatform.Model.CourseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseItemRepository extends JpaRepository<CourseItem, Integer> {

    CourseItem findCourseItemById(Integer id);

    CourseItem findCourseItemByIdAndCourseId(Integer id, Integer courseID);

}
