package com.example.schooltest.services;

import com.example.schooltest.converter.StudentDTOtoStudent;
import com.example.schooltest.converter.StudentToStudentDTO;
import com.example.schooltest.converter.StudentToStudentFileDTO;
import com.example.schooltest.dto.StudentDTO;
import com.example.schooltest.dto.StudentFileDTO;
import com.example.schooltest.exceptions.NotFoundException;
import com.example.schooltest.models.Student;
import com.example.schooltest.repositories.StudentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentToStudentDTO toStudentDTO;
    private final StudentDTOtoStudent toStudent;
    private final StudentToStudentFileDTO toStudentFileDTO;

    public StudentService(StudentRepository studentRepository, StudentToStudentDTO toStudentDTO, StudentDTOtoStudent toStudent, StudentToStudentFileDTO toStudentFileDTO) {
        this.studentRepository = studentRepository;
        this.toStudentDTO = toStudentDTO;
        this.toStudent = toStudent;
        this.toStudentFileDTO = toStudentFileDTO;
    }

    public List<StudentDTO> findAll(){
        Sort sort = Sort.by("name").ascending();
        saveFile();
        return studentRepository.findAll(sort).stream()
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

    public String saveFile(){
        Sort sort = Sort.by("name").ascending();
        List<StudentFileDTO> students = studentRepository.findAll(sort).stream()
                .map(student -> toStudentFileDTO.convert(student)).collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();

        for (StudentFileDTO st : students) {
            stringBuilder.append(st.getName()).append(st.getPaidFees().size()!=0 ? "\n\tFees Paid:\n" : "\n");
            for (double fee : st.getPaidFees()) {
                stringBuilder.append("\t$").append(String.format("%.2f", fee)).append("\n");
            }
        }
        String formattedString = stringBuilder.toString();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = formatter.format(LocalDateTime.now());

        try {
            File file = new File("data/student_" + timestamp + ".txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(formattedString);
            writer.close();
            return "File saved";
        } catch (IOException e) {
            e.printStackTrace();

            return e.getMessage();
        }
    }
}
