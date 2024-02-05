package com.assignment.courseManagement.controller;

import com.assignment.courseManagement.beans.req.StudentRequest;
import com.assignment.courseManagement.beans.res.CourseResponse;
import com.assignment.courseManagement.beans.res.StudentResponse;
import com.assignment.courseManagement.model.Course;
import com.assignment.courseManagement.model.Student;
import com.assignment.courseManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // Task 1 : Create a new student using the POST mapping
    @PostMapping("/")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest studentRequest) {
        StudentResponse newStudent = studentService.createStudent(studentRequest);
        return new ResponseEntity<>(newStudent, HttpStatus.OK);
    }

    // Task 2 : Fetch a single students using the GET mapping
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable Long id) {
        StudentResponse newStudent = studentService.getStudent(id);
        return new ResponseEntity<>(newStudent, HttpStatus.OK);
    }

    // Task 3 : Fetch all students using the GET mapping
    @GetMapping("/")
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // Task 4 : Delete a particular student using the DELETE mapping
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    //Task 5 : get courses for a particular student using the GET mapping
    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CourseResponse>> getCoursesForStudent(@PathVariable Long id) {
        List<CourseResponse> responses = studentService.getCoursesForStudent(id);
        return ResponseEntity.ok(responses);
    }
}