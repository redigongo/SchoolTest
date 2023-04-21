package com.example.schooltest.services;

import com.example.schooltest.converter.StudentFeeDTOtoStudentFee;
import com.example.schooltest.dto.StudentFeeDTO;
import com.example.schooltest.models.School;
import com.example.schooltest.models.Student;
import com.example.schooltest.models.StudentFee;
import com.example.schooltest.repositories.SchoolRepository;
import com.example.schooltest.repositories.StudentFeeRepository;
import com.example.schooltest.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentFeeService {
    private final StudentFeeRepository studentFeeRepository;
    private final SchoolRepository schoolRepository;
    private final StudentFeeDTOtoStudentFee toStudentFee;
    private final StudentRepository studentRepository;

    public String saveOrUpdate(StudentFeeDTO studentFeeDTO){
        StudentFee studentFee = toStudentFee.convert(studentFeeDTO);
        Student studentToUpdate = studentFee.getStudent();
        School schoolToUpdate = studentToUpdate.getSchool();
        double feeAmount = studentFeeDTO.getAmount();
        double schoolEarnings = schoolToUpdate.getTotalMoneyEarned();

        if (studentToUpdate.getFeesRemaining() - feeAmount >= 0) {
            studentToUpdate.setFeesRemaining(studentToUpdate.getFeesRemaining() - feeAmount);
            studentToUpdate.setFeesPaid(studentToUpdate.getFeesPaid() + feeAmount);
            schoolToUpdate.setTotalMoneyEarned(schoolEarnings + feeAmount);

            studentFeeRepository.save(studentFee);
            studentRepository.save(studentToUpdate);
            schoolRepository.save(schoolToUpdate);

        }else {
            return "The fee amount is larger than the remaining Fee";
        }
        return "Fee saved!";
    }
}
