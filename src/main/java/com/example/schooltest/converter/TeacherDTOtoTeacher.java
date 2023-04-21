package com.example.schooltest.converter;

import com.example.schooltest.dto.TeacherDTO;
import com.example.schooltest.exceptions.NotFoundException;
import com.example.schooltest.models.Teacher;
import com.example.schooltest.repositories.SchoolRepository;
import com.example.schooltest.repositories.SubjectRepository;
import com.example.schooltest.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TeacherDTOtoTeacher implements Converter<TeacherDTO, Teacher> {
    private final SchoolRepository schoolRepository;
    private final SubjectRepository subjectRepository;
    @Override
    public Teacher convert(TeacherDTO source) {
        if (source!=null){
            Teacher teacher = new Teacher();
            teacher.setName(source.getName());
            teacher.setSalary(source.getSalary());
            teacher.setSchool(schoolRepository.findById(source.getSchoolId()).orElseThrow(()->
                    new NotFoundException("School with id: " + source.getSchoolId() + " does not exist")));

            teacher.setSubject(subjectRepository.findById(source.getSubjectId()).orElseThrow(()->
                    new NotFoundException("School with id: " + source.getSubjectId() + " does not exist")));

            return teacher;
        }
        return null;
    }
}
