package com.assignment.courseManagement.service.impl;

import com.assignment.courseManagement.beans.req.EnrollmentRequest;
import com.assignment.courseManagement.beans.res.EnrollmentResponse;
import com.assignment.courseManagement.model.Course;
import com.assignment.courseManagement.model.Enrollment;
import com.assignment.courseManagement.model.Student;
import com.assignment.courseManagement.respository.CourseRepository;
import com.assignment.courseManagement.respository.EnrollmentRepository;
import com.assignment.courseManagement.respository.StudentRepository;
import com.assignment.courseManagement.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
                                 StudentRepository studentRepository,
                                 CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    // Task 13: Enroll a specified student in a course by using POST mapping
    @Override
    public EnrollmentResponse enrollStudentInCourse(EnrollmentRequest enrollmentRequest) {
        long sid = enrollmentRequest.getStudentId();
        long cid = enrollmentRequest.getCourseId();
        Optional<Student> optionalStudent = studentRepository.findById(sid);
        Optional<Course> optionalCourse = courseRepository.findById(cid);
        Enrollment enrollment = new Enrollment();

        if(optionalStudent.isPresent()) {
            enrollment.setStudent(optionalStudent.get());
        }
        else {
            throw new IllegalArgumentException("No Student found with this Id: " + sid);
        }

        if(optionalCourse.isPresent()) {
            enrollment.setCourse(optionalCourse.get());
        }
        else {
            throw new IllegalArgumentException("No Course found with this Id: " + cid);
        }
        enrollment.setMarks(enrollmentRequest.getMarks());

        var save = enrollmentRepository.save(enrollment);

        EnrollmentResponse response = new EnrollmentResponse();
        response.setEnrollmentId(save.getEnrollmentId());
        response.setStudentId(save.getStudent().getStudentId());
        response.setCourseId(save.getCourse().getCourseId());
        response.setMarks(save.getMarks());

        return response;

    }

    // Task 14: Unenroll a specified student in a course by using Delete mapping
    @Override
    public void unenrollStudentFromCourse(Long studentId, Long courseId) {

        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(optionalCourse.isPresent() && optionalStudent.isPresent()) {
            Course course = optionalCourse.get();
            Student student = optionalStudent.get();
            Enrollment enrollment = enrollmentRepository.findByStudentAndCourse(student, course);
            enrollmentRepository.delete(enrollment);
        }
        else {
            throw new IllegalArgumentException("Enrollment not found for Student ID: " + studentId + " and Course ID: " + courseId);
        }

    }

    // Task 15: Update marks for a particular student in a specified course by using PUT mapping
    @Override
    public EnrollmentResponse updateMarks(EnrollmentRequest enrollmentRequest) {

        Optional<Course> optionalCourse = courseRepository.findById(enrollmentRequest.getCourseId());
        Optional<Student> optionalStudent = studentRepository.findById(enrollmentRequest.getStudentId());

        if (optionalCourse.isPresent() && optionalStudent.isPresent()) {
            Enrollment enrollment = enrollmentRepository.findByStudentAndCourse(optionalStudent.get(), optionalCourse.get());
            enrollment.setMarks(enrollmentRequest.getMarks());
            enrollmentRepository.save(enrollment);

            EnrollmentResponse response = new EnrollmentResponse();
            response.setEnrollmentId(enrollment.getEnrollmentId());
            response.setStudentId(enrollment.getStudent().getStudentId());
            response.setCourseId(enrollment.getCourse().getCourseId());
            response.setMarks(enrollment.getMarks());

            return response;
        } else {
            throw new IllegalArgumentException("Enrollment not found for Student ID: " + enrollmentRequest.getStudentId() +
                    " and Course ID: " + enrollmentRequest.getCourseId());
        }
    }

}
