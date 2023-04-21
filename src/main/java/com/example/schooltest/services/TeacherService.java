package com.example.schooltest.services;

import com.example.schooltest.converter.TeacherDTOtoTeacher;
import com.example.schooltest.converter.TeacherToTeacherDTO;
import com.example.schooltest.dto.TeacherDTO;
import com.example.schooltest.exceptions.NotFoundException;
import com.example.schooltest.models.School;
import com.example.schooltest.models.Teacher;
import com.example.schooltest.repositories.SchoolRepository;
import com.example.schooltest.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final SchoolRepository schoolRepository;
    private final TeacherToTeacherDTO toTeacherDTO;
    private final TeacherDTOtoTeacher toTeacher;

    public List<TeacherDTO> findAll(){
        return teacherRepository.findAll().stream()
                .map(teacher -> toTeacherDTO.convert(teacher)).collect(Collectors.toList());
    }

    public TeacherDTO findById(Long id){
        return toTeacherDTO.convert(teacherRepository.findById(id).orElseThrow(()->
                new NotFoundException("Teacher with id: " + id+ " does not exist!")));
    }

    public void saveOrUpdate(TeacherDTO teacherDTO){
        Teacher teacher = toTeacher.convert(teacherDTO);
        School schoolToUpdate = teacher.getSchool();
        double schoolTotalMoneySpent = schoolToUpdate.getTotalMoneySpent();
        schoolToUpdate.setTotalMoneySpent(schoolTotalMoneySpent + teacher.getSalary());
        teacherRepository.save(teacher);
        schoolRepository.save(schoolToUpdate);
    }

    public void delete(Long id){
        teacherRepository.deleteById(id);
    }
}
