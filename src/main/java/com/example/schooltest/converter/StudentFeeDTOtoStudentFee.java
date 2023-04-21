package com.example.schooltest.converter;

import com.example.schooltest.dto.StudentFeeDTO;
import com.example.schooltest.exceptions.NotFoundException;
import com.example.schooltest.models.StudentFee;
import com.example.schooltest.repositories.StudentFeeRepository;
import com.example.schooltest.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentFeeDTOtoStudentFee implements Converter<StudentFeeDTO, StudentFee> {
    private final StudentRepository studentRepository;
    @Override
    public StudentFee convert(StudentFeeDTO source) {
        if (source!=null){
            StudentFee studentFee = new StudentFee();
            if (source.getId()!=null){
                studentFee.setId(source.getId());
            }
            studentFee.setAmount(source.getAmount());
            studentFee.setStudent(studentRepository.findById(source.getStudentId()).orElseThrow(()->
                    new NotFoundException("Student with id: " + source.getStudentId() + " does not exists")));
            return studentFee;
        }

        return null;
    }
}
