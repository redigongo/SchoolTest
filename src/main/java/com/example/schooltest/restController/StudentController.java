package com.example.schooltest.restController;

import com.example.schooltest.dto.StudentDTO;
import com.example.schooltest.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentDTO> findAll(){
        return studentService.findAll();
    }

    @PostMapping
    public void saveOrUpdate(@RequestBody StudentDTO studentDTO){
        studentService.saveOrUpdate(studentDTO);
    }
    @GetMapping("/{id}")
    public StudentDTO findById(@PathVariable Long id){
        return studentService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        studentService.delete(id);
    }

}
