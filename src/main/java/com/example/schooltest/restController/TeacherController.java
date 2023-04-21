package com.example.schooltest.restController;

import com.example.schooltest.dto.TeacherDTO;
import com.example.schooltest.services.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public List<TeacherDTO> findAll(){
        return teacherService.findAll();
    }

    @GetMapping("/{id}")
    public TeacherDTO findById(@PathVariable Long id){
        return teacherService.findById(id);
    }

    @PostMapping
    public void saveOrUpdate(@RequestBody TeacherDTO teacherDTO){
        teacherService.saveOrUpdate(teacherDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        teacherService.delete(id);
    }

}
