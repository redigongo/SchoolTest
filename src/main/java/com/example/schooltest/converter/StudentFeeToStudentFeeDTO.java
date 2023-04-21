package com.example.schooltest.converter;

import com.example.schooltest.dto.StudentFeeDTO;
import com.example.schooltest.models.StudentFee;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudentFeeToStudentFeeDTO implements Converter<StudentFee, StudentFeeDTO> {
    @Override
    public StudentFeeDTO convert(StudentFee source) {
        if(source!=null){
            StudentFeeDTO studentFeeDTO = new StudentFeeDTO();
            if (source.getId()!=null){
                studentFeeDTO.setId(source.getId());
            }
            studentFeeDTO.setAmount(source.getAmount());
            studentFeeDTO.setStudentId(source.getStudent()!=null ? source.getStudent().getId() : null);
            return studentFeeDTO;
        }
        return null;
    }
}
