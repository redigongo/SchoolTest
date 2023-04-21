package com.example.schooltest.converter;

import com.example.schooltest.dto.StudentDTO;
import com.example.schooltest.models.Student;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class StudentToStudentDTO implements Converter<Student, StudentDTO> {
    private final StudentFeeToStudentFeeDTO toStudentFeeDTO;
    @Override
    public StudentDTO convert(Student source) {
        if (source!=null){
            StudentDTO studentDTO = new StudentDTO();
            if (source.getId()!=null){
                studentDTO.setId(source.getId());
            }
            studentDTO.setName(source.getName());
            studentDTO.setFeesPaid(source.getFeesPaid());
            studentDTO.setFeesRemaining(source.getFeesRemaining());
            studentDTO.setSubjectId(source.getSubject()!=null ? source.getSubject().getId() : null);
            studentDTO.setSchoolId(source.getSchool()!=null ? source.getSchool().getId() : null);

            studentDTO.setStudentFeeList(source.getStudentFeeList().stream()
                    .map(studentFee -> toStudentFeeDTO.convert(studentFee)).collect(Collectors.toList()));

            return studentDTO;
        }

        return null;
    }
}
