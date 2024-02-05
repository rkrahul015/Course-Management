package com.assignment.courseManagement.controller;

import com.assignment.courseManagement.beans.req.EnrollmentRequest;
import com.assignment.courseManagement.beans.res.EnrollmentResponse;
import com.assignment.courseManagement.model.Enrollment;
import com.assignment.courseManagement.service.EnrollmentService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // Task 13: Enroll a specified student in a course by using POST mapping
    @PostMapping("/enroll")
    public EnrollmentResponse enrollStudentInCourse(@RequestBody EnrollmentRequest enrollmentRequest) {
        EnrollmentResponse response = enrollmentService.enrollStudentInCourse(enrollmentRequest);
        return response;
    }

    // Task 14: Unenroll a specified student from a course by using DELETE mapping
    @DeleteMapping("/unenroll")
    public ResponseEntity<Void> unenrollStudentFromCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        enrollmentService.unenrollStudentFromCourse(studentId,courseId);
        return ResponseEntity.noContent().build();
    }

    //Task 15: Update marks of a particular student in a specified course by using PUT mapping
    @PutMapping("/updateMarks")
    public ResponseEntity<EnrollmentResponse> updateMarks(@RequestBody EnrollmentRequest enrollmentRequest) {
        EnrollmentResponse enrollmentResponse =   enrollmentService.updateMarks(enrollmentRequest);
        return ResponseEntity.ok(enrollmentResponse);
    }
}
