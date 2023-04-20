package com.example.schooltest.dto;

import lombok.Data;

@Data
public class TeacherDTO {
    Long id;
    String name;
    Long subjectId;
    Long schoolId;
    double salary;
}
