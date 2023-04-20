package com.example.schooltest.dto;


import lombok.Data;

@Data
public class StudentFeeDTO {
    Long id;
    double amount;
    Long studentId;

}
