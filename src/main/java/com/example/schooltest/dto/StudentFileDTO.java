package com.example.schooltest.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentFileDTO {
    String name;
    List<String> paidFees;
}
