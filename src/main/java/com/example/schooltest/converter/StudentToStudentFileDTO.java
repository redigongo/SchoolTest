package com.example.schooltest.converter;

import com.example.schooltest.dto.StudentFileDTO;
import com.example.schooltest.models.Student;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class StudentToStudentFileDTO implements Converter<Student, StudentFileDTO> {
    private final StudentFeeToStudentFeeDTO toStudentFeeDTO;
    @Override
    public StudentFileDTO convert(Student source) {
        if (source!=null){
            StudentFileDTO studentFileDTO = new StudentFileDTO();
            studentFileDTO.setName(source.getName());
            studentFileDTO.setPaidFees(source.getStudentFeeList().stream()
                    .map(studentFee -> "Amount: " + String.valueOf(studentFee.getAmount())).collect(Collectors.toList()));
            return studentFileDTO;
        }
        return null;
    }
}
