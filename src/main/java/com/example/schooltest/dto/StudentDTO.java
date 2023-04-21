package com.example.schooltest.dto;

import com.example.schooltest.models.StudentFee;
import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {
    Long id;
    String name;
    double feesPaid;
    double feesRemaining;
    List<StudentFeeDTO> studentFeeList;
    Long subjectId;
    Long schoolId;
}
