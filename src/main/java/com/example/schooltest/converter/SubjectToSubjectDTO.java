package com.example.schooltest.converter;

import com.example.schooltest.dto.SubjectDTO;
import com.example.schooltest.models.Subject;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SubjectToSubjectDTO implements Converter<Subject, SubjectDTO> {
    private StudentToStudentDTO toStudentDTO;
    @Override
    public SubjectDTO convert(Subject source) {
        if (source!=null){
            SubjectDTO subjectDTO = new SubjectDTO();
            if (source.getId()!=null){
                subjectDTO.setId(source.getId());
            }
            subjectDTO.setName(source.getName());
            subjectDTO.setTeacherId(source.getTeacher()!=null ? source.getTeacher().getId() : null);
            subjectDTO.setStudents(source.getStudents().stream()
                    .map(student -> toStudentDTO.convert(student)).collect(Collectors.toList()));
            return subjectDTO;
        }
        return null;
    }
}
