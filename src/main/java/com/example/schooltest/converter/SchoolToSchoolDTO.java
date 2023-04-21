package com.example.schooltest.converter;

import com.example.schooltest.dto.SchoolDTO;
import com.example.schooltest.models.School;
import com.example.schooltest.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SchoolToSchoolDTO implements Converter<School, SchoolDTO> {
    private TeacherToTeacherDTO toTeacherDTO;
    private StudentToStudentDTO toStudentDTO;
    @Override
    public SchoolDTO convert(School source) {
        if (source!=null){
            SchoolDTO schoolDTO = new SchoolDTO();
            if (source.getId()!=null){
                schoolDTO.setId(source.getId());
            }
            schoolDTO.setName(source.getName());
            schoolDTO.setTotalMoneySpent(source.getTotalMoneySpent());
            schoolDTO.setTotalMoneyEarned(source.getTotalMoneyEarned());

            schoolDTO.setTeachers(source.getTeachers().stream()
                    .map(teacher -> toTeacherDTO.convert(teacher)).collect(Collectors.toList()));

            schoolDTO.setStudents(source.getStudents().stream()
                    .map(student -> toStudentDTO.convert(student)).collect(Collectors.toList()));

            double totalSpent= source.getTotalMoneySpent();
            double totalEarned = source.getTotalMoneyEarned();
            if (totalEarned - totalSpent >= 0) {
                schoolDTO.setNetEarnings(totalEarned - totalSpent);
            }else {
                schoolDTO.setNetEarnings(0);
            }
            if (totalSpent - totalEarned >= 0 ){
                schoolDTO.setNetLosses(totalSpent-totalEarned);
            }else {
                schoolDTO.setNetLosses(0);
            }
            return schoolDTO;

        }
        return null;
    }
}
