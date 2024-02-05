package com.assignment.courseManagement.respository;

import com.assignment.courseManagement.model.Course;
import com.assignment.courseManagement.model.Enrollment;
import com.assignment.courseManagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>  {

    List<Enrollment> findByStudent(Student student);
    List<Enrollment> findByCourse(Course course);

    @Query(value = "select max(e.marks) from Enrollment e where e.course = ?1")
    int getMaxMarksForCourse(Course course);

    Enrollment findByStudentAndCourse(Student student, Course course);



}
