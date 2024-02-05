package com.assignment.courseManagement.controller;

import com.assignment.courseManagement.beans.req.CourseRequest;
import com.assignment.courseManagement.beans.res.CourseResponse;
import com.assignment.courseManagement.beans.res.StudentResponse;
import com.assignment.courseManagement.model.Course;
import com.assignment.courseManagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Task 6 : Create a new course using the POST mapping
    @PostMapping("/")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest) {
        CourseResponse response = courseService.createCourse(courseRequest);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // Task 7 : Fetch a single course using the GET mapping
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable Long id) {
        CourseResponse course = courseService.getCourse(id);
        return new ResponseEntity<>(course, HttpStatus.OK);

    }

    // Task 8 : Fetch all courses using the GET mapping
    @GetMapping("/")
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        List<CourseResponse> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    // Task 9 : Delete a particular course using the DELETE mapping
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }


    // Task 10 : Get students for a specific course using GET mapping
    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentResponse>> getStudentsForCourse(@PathVariable Long id) {
        List<StudentResponse> responses = courseService.getStudentsForCourse(id);
        return ResponseEntity.ok(responses);
    }

    // Task 11 : Get the most enrolled course using GET mapping
    @GetMapping("/most-enrolled")
    public CourseResponse getMostEnrolledCourse() {
        CourseResponse courseResponse = courseService.getMostEnrolledCourse();
        return  courseResponse;
    }

    //Task : 12 Get the highest marks scored in a specific course by a student using GET mapping
    @GetMapping("/highest-marks/{id}")
    public Integer getMaxMarksForCourse(@PathVariable long id) {
        Integer marks = courseService.getMaxMarksForCourse(id);
        return marks;
    }
}
