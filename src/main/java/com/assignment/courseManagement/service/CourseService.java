package com.assignment.courseManagement.service;

import com.assignment.courseManagement.beans.req.CourseRequest;
import com.assignment.courseManagement.beans.res.CourseResponse;
import com.assignment.courseManagement.beans.res.StudentResponse;
import com.assignment.courseManagement.model.Course;
import com.assignment.courseManagement.model.Enrollment;
import com.assignment.courseManagement.model.Student;
import com.assignment.courseManagement.respository.CourseRepository;
import com.assignment.courseManagement.respository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    //Task 6 :To create a Course using POST mapping
    public CourseResponse createCourse(CourseRequest courseRequest) {

        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setCredits(courseRequest.getCredits());

        var save = courseRepository.save(course);

        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setCourseId(save.getCourseId());
        courseResponse.setCourseName(save.getCourseName());
        courseResponse.setCredits(save.getCredits());

        return courseResponse;
    }

    //Task 7 :  Fetch a single course of particular Id using GET mapping
    public CourseResponse getCourse(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isPresent()) {
            var course = optionalCourse.get();
            CourseResponse response = new CourseResponse();
            response.setCourseId(course.getCourseId());
            response.setCourseName(course.getCourseName());
            response.setCredits(course.getCredits());
            return response;
        }
        else {
            throw new IllegalArgumentException("No Course found with this Id: " + id);
        }
    }

    //Task 8 : Fetch all courses using GET mapping
    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> courseResponses = new ArrayList<>();
        for(Course course : courses) {
            CourseResponse response = new CourseResponse();
            response.setCourseId(course.getCourseId());
            response.setCourseName(course.getCourseName());
            response.setCredits(course.getCredits());
            courseResponses.add(response);
        }
        return courseResponses;
    }

    //Task 9 :To delete a Course using DELETE mapping
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    // Task 10 : Get students for a specific course using GET mapping
    public List<StudentResponse> getStudentsForCourse(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);

        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            List<Enrollment> enrollments = enrollmentRepository.findByCourse(course);
            List<StudentResponse> responses = new ArrayList<>();
            for (Enrollment enrollment: enrollments) {
                Student student = enrollment.getStudent();
                StudentResponse response = new StudentResponse();
                response.setStudentId(student.getStudentId());
                response.setFirstName(student.getFirstName());
                response.setLastName(student.getLastName());
                response.setEmail(student.getEmail());
                responses.add(response);
            }
            return  responses;

        } else {
            throw new IllegalArgumentException("No Student found");
        }
    }

    // Task 11 : Get the most enrolled course using GET mapping
    public CourseResponse getMostEnrolledCourse() {
        Optional<Course> optionalCourse= courseRepository.findMostEnrolledCourses();
        if (optionalCourse.isPresent()) {
            Course course=optionalCourse.get();
            CourseResponse response = new CourseResponse();
            response.setCourseId(course.getCourseId());
            response.setCourseName(course.getCourseName());
            response.setCredits(course.getCredits());
            return response;
        }
        else {
            throw new IllegalArgumentException("No Course found");
        }

    }

    //Task : 12 Get the highest marks scored in a specific course by a student using GET mapping
    public Integer getMaxMarksForCourse(long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isPresent()) {
            int marks = enrollmentRepository.getMaxMarksForCourse(optionalCourse.get());
            return  marks;
        }
        else {
            throw new IllegalArgumentException("No Course Found");
        }
    }
}
