package com.example.schooltest.services;

import com.example.schooltest.converter.StudentDTOtoStudent;
import com.example.schooltest.converter.StudentToStudentDTO;
import com.example.schooltest.dto.StudentDTO;
import com.example.schooltest.exceptions.NotFoundException;
import com.example.schooltest.models.Student;
import com.example.schooltest.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentToStudentDTO toStudentDTO;
    private final StudentDTOtoStudent toStudent;

    public StudentService(StudentRepository studentRepository, StudentToStudentDTO toStudentDTO, StudentDTOtoStudent toStudent) {
        this.studentRepository = studentRepository;
        this.toStudentDTO = toStudentDTO;
        this.toStudent = toStudent;
    }

    public List<StudentDTO> findAll(){
        return studentRepository.findAll().stream()
                .map(student -> toStudentDTO.convert(student)).collect(Collectors.toList());
    }

    public StudentDTO findById(Long id){
        return toStudentDTO.convert(studentRepository.findById(id).orElseThrow(()->
                new NotFoundException("Student with id: " + id + " does not exists")));
    }

    public void saveOrUpdate(StudentDTO studentDTO){
        Student student = toStudent.convert(studentDTO);
        studentRepository.save(student);
    }

    public void delete(Long id){
        studentRepository.deleteById(id);
    }

}
