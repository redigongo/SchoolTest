package com.example.schooltest.converter;

import com.example.schooltest.dto.TeacherDTO;
import com.example.schooltest.models.Teacher;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TeacherToTeacherDTO implements Converter<Teacher, TeacherDTO> {
    @Override
    public TeacherDTO convert(Teacher source) {
        if (source!=null){
            TeacherDTO teacherDTO = new TeacherDTO();
            if (source.getId()!=null){
                teacherDTO.setId(source.getId());
            }
            teacherDTO.setName(source.getName());
            teacherDTO.setSalary(source.getSalary());
            teacherDTO.setSchoolId(source.getSchool()!=null ? source.getSchool().getId() : null);
            teacherDTO.setSubjectId(source.getSubject()!=null ? source.getSubject().getId() : null);
            return  teacherDTO;
        }
        return null;
    }
}
