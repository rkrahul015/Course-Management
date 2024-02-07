package com.assignment.courseManagement.service;

import com.assignment.courseManagement.beans.req.EnrollmentRequest;
import com.assignment.courseManagement.beans.res.EnrollmentResponse;

public interface EnrollmentService {

    EnrollmentResponse enrollStudentInCourse(EnrollmentRequest enrollmentRequest);
    void unenrollStudentFromCourse(Long studentId, Long courseId);
    EnrollmentResponse updateMarks(EnrollmentRequest enrollmentRequest);


}
