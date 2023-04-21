package com.example.schooltest.restController;

import com.example.schooltest.dto.StudentFeeDTO;
import com.example.schooltest.services.StudentFeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/studentFee")
public class StudentFeeController {
    private final StudentFeeService studentFeeService;


    @PostMapping()
    public String saveOrUpdate(@RequestBody StudentFeeDTO studentFeeDTO) {
        return studentFeeService.saveOrUpdate(studentFeeDTO);
    }

}
