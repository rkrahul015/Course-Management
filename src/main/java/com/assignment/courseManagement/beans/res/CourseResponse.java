package com.assignment.courseManagement.beans.res;

import com.assignment.courseManagement.model.Enrollment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

    private Long courseId;
    private String courseName;
    private double credits;

}
