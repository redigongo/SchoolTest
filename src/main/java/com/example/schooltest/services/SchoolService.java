package com.example.schooltest.services;

import com.example.schooltest.converter.SchoolDTOtoSchool;
import com.example.schooltest.converter.SchoolToSchoolDTO;
import com.example.schooltest.dto.SchoolDTO;
import com.example.schooltest.exceptions.NotFoundException;
import com.example.schooltest.repositories.SchoolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolDTOtoSchool tOtoSchool;
    private final SchoolToSchoolDTO toSchoolDTO;

    public List<SchoolDTO> findAll(){
        return schoolRepository.findAll().stream()
                .map(school -> toSchoolDTO.convert(school)).collect(Collectors.toList());
    }

    public SchoolDTO findById(Long id){
        return toSchoolDTO.convert(schoolRepository.findById(id).orElseThrow(()->
                new NotFoundException("School with id: " + id + " does not exist")));
    }

    public void saveOrUpdate(SchoolDTO schoolDTO){
        schoolRepository.save(tOtoSchool.convert(schoolDTO));
    }

    public void delete(Long id){
        schoolRepository.deleteById(id);
    }
}
