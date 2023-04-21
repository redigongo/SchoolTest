package com.example.schooltest.restController;

import com.example.schooltest.dto.SchoolDTO;
import com.example.schooltest.repositories.SchoolRepository;
import com.example.schooltest.services.SchoolService;
import jakarta.persistence.Access;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/school")
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping
    public List<SchoolDTO> findAll(){

        return schoolService.findAll();
    }

    @PostMapping
    public void saveOrUpdate(@RequestBody SchoolDTO schoolDTO){
        schoolService.saveOrUpdate(schoolDTO);
    }

    @GetMapping("/{id}")
    public SchoolDTO findById(@PathVariable Long id){
        return schoolService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        schoolService.delete(id);
    }
}
