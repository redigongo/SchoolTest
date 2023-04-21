package com.example.schooltest.restController;

import com.example.schooltest.dto.SubjectDTO;
import com.example.schooltest.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping
    public List<SubjectDTO> findAll(){
        return subjectService.findAll();
    }

    @GetMapping("/{id}")
    public SubjectDTO findById(@PathVariable Long id){
        return subjectService.findById(id);
    }

    @PostMapping
    public void saveOrUpdate(@RequestBody SubjectDTO subjectDTO){
        subjectService.saveOrUpdate(subjectDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        subjectService.delete(id);
    }
}
