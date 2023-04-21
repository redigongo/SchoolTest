package com.example.schooltest.dto;

import lombok.Data;

import java.util.List;

@Data
public class SchoolDTO {
    Long id;
    String name;
    double totalMoneyEarned;
    double totalMoneySpent;

    double netEarnings;
    double netLosses;
    List<StudentDTO> students;
    List<TeacherDTO> teachers;
}
