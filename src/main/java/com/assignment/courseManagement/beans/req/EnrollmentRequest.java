package com.assignment.courseManagement.beans.req;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentRequest
{
    private Long studentId;
    private Long courseId;
    private int marks;
}
