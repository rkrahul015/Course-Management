package com.assignment.courseManagement.service.impl;

import com.assignment.courseManagement.beans.req.StudentRequest;
import com.assignment.courseManagement.beans.res.CourseResponse;
import com.assignment.courseManagement.beans.res.StudentResponse;
import com.assignment.courseManagement.model.Course;
import com.assignment.courseManagement.model.Enrollment;
import com.assignment.courseManagement.model.Student;
import com.assignment.courseManagement.respository.EnrollmentRepository;
import com.assignment.courseManagement.respository.StudentRepository;
import com.assignment.courseManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    //Task 1 :To create a Student using POST mapping
    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        Student save = studentRepository.save(student);

        StudentResponse response = new StudentResponse();
        response.setStudentId(save.getStudentId());
        response.setFirstName(save.getFirstName());
        response.setLastName(save.getLastName());
        response.setEmail(save.getEmail());
        return response;
    }
    @Override
    //Task 2 :  Fetch a single student of particular Id using GET mapping
    public StudentResponse getStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()) {
            var student = optionalStudent.get();
            StudentResponse response = new StudentResponse();
            response.setStudentId(student.getStudentId());
            response.setFirstName(student.getFirstName());
            response.setLastName(student.getLastName());
            response.setEmail(student.getEmail());
            return response;
        }
        else {
            throw new IllegalArgumentException("No Student found with this Id: " + id);
        }
    }

    //Task 3 : Fetch all students using GET mapping
    @Override
    public List<StudentResponse> getAllStudents() {
        List<Student> students =  studentRepository.findAll();
        List<StudentResponse> studentResponses = new ArrayList<>();
        for(Student student : students) {
           StudentResponse response = new StudentResponse();
           response.setStudentId(student.getStudentId());
           response.setFirstName(student.getFirstName());
           response.setLastName(student.getLastName());
           response.setEmail(student.getEmail());
           studentResponses.add(response);
        }
        return studentResponses;
    }

    //Task 4 :To delete a Student using DELETE mapping
    @Override
    public void deleteStudent(Long id) {

        studentRepository.deleteById(id);
    }

    //Task 5 : get courses for a particular student using the GET mapping
    public List<CourseResponse> getCoursesForStudent(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            List<Enrollment> enrollments = enrollmentRepository.findByStudent(student);
            List<CourseResponse> responses = new ArrayList<>();
            for (Enrollment enrollment: enrollments) {
                Course course = enrollment.getCourse();
                CourseResponse response = new CourseResponse();
                response.setCourseId(course.getCourseId());
                response.setCourseName(course.getCourseName());
                response.setCredits(course.getCredits());
                responses.add(response);
            }
            return  responses;

        } else {
            throw new IllegalArgumentException("No Student found");
        }
    }





}
