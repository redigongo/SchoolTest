package com.example.schooltest.converter;

import com.example.schooltest.dto.StudentDTO;
import com.example.schooltest.exceptions.NotFoundException;
import com.example.schooltest.models.Student;
import com.example.schooltest.repositories.SchoolRepository;
import com.example.schooltest.repositories.StudentFeeRepository;
import com.example.schooltest.repositories.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentDTOtoStudent implements Converter<StudentDTO, Student> {

    private final SubjectRepository subjectRepository;
    private final SchoolRepository schoolRepository;
    private final StudentFeeRepository studentFeeRepository;
    @Override
    public Student convert(StudentDTO source) {
        if (source!=null){
            Student student = new Student();
            if (source.getId()!=null){
                student.setId(source.getId());
            }
            student.setName(source.getName());
            student.setSubject(subjectRepository.findById(source.getSubjectId()).orElseThrow(()->
                    new NotFoundException("Subject with id: " + source.getSubjectId() + " does not exist")));
            student.setSchool(schoolRepository.findById(source.getSchoolId()).orElseThrow(()->
                    new NotFoundException("School with id: " + source.getSchoolId() + " does not exist")));

            double feesPaid = studentFeeRepository.countAllByStudentId(source.getId());
            student.setFeesPaid(feesPaid);


            if (feesPaid == 0 ) {
                student.setFeesRemaining(5000);
            }else {
                student.setFeesRemaining(5000 - feesPaid);
            }
            return student;


        }

        return null;
    }
}
