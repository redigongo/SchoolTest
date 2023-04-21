package com.example.schooltest.converter;

import com.example.schooltest.dto.SubjectDTO;
import com.example.schooltest.exceptions.NotFoundException;
import com.example.schooltest.models.Subject;
import com.example.schooltest.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SubjectDTOToSubject implements Converter<SubjectDTO,Subject> {
    private final TeacherRepository teacherRepository;
    @Override
    public Subject convert(SubjectDTO source) {
        if (source!=null){
            Subject subject = new Subject();
            if (source.getId()!=null){
                subject.setId(source.getId());
            }
            subject.setName(source.getName());
            subject.setTeacher(teacherRepository.findById(source.getTeacherId()).orElseThrow(()->
                    new NotFoundException("Teacher with id: " + source.getTeacherId() + " does not exists")));
            return subject;
        }
        return null;
    }
}
