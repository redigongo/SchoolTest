package com.example.schooltest.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubjectDTO {
    Long id;
    String name;
    Long teacherId;
    List<StudentDTO> students;
}
