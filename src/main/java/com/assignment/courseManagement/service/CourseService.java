package com.assignment.courseManagement.service;

import com.assignment.courseManagement.beans.req.CourseRequest;
import com.assignment.courseManagement.beans.res.CourseResponse;
import com.assignment.courseManagement.beans.res.StudentResponse;

import java.util.List;

public interface CourseService {
    CourseResponse createCourse(CourseRequest courseRequest);
    CourseResponse getCourse(Long id);
    List<CourseResponse> getAllCourses();
    void deleteCourse(Long id);
    CourseResponse getMostEnrolledCourse();
    Integer getMaxMarksForCourse(long id);
    List<StudentResponse> getStudentsForCourse(Long id);
}
