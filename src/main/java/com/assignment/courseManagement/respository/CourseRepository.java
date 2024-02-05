package com.assignment.courseManagement.respository;

import com.assignment.courseManagement.model.Course;
import com.assignment.courseManagement.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "select c from Course c INNER JOIN Enrollment e ON e.course=c group by c order by count(e) desc limit 1")
    Optional<Course> findMostEnrolledCourses();
}
