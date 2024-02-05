package com.assignment.courseManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    //Many-to-one relationship with the Student
    @ManyToOne
    private Student student;

    //Many-to-one relationship with the Course
    @ManyToOne
    private Course course;

    private int marks;
}
