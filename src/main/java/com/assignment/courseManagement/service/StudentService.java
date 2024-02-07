package com.assignment.courseManagement.service;

import com.assignment.courseManagement.beans.req.StudentRequest;
import com.assignment.courseManagement.beans.res.CourseResponse;
import com.assignment.courseManagement.beans.res.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(StudentRequest studentRequest);
    StudentResponse getStudent(Long id);
    List<StudentResponse> getAllStudents();

    void deleteStudent(Long id);

    List<CourseResponse> getCoursesForStudent(Long id);
}
