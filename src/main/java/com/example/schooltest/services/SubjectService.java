package com.example.schooltest.services;

import com.example.schooltest.converter.SubjectDTOToSubject;
import com.example.schooltest.converter.SubjectToSubjectDTO;
import com.example.schooltest.dto.SubjectDTO;
import com.example.schooltest.exceptions.NotFoundException;
import com.example.schooltest.repositories.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectToSubjectDTO toSubjectDTO;
    private final SubjectDTOToSubject toSubject;

    public List<SubjectDTO> findAll(){
        return subjectRepository.findAll().stream()
                .map(subject -> toSubjectDTO.convert(subject)).collect(Collectors.toList());
    }

    public SubjectDTO findById(Long id){
        return toSubjectDTO.convert(subjectRepository.findById(id).orElseThrow(()->
                new NotFoundException("Subject with id: " + id + " does not exist")));
    }

    public void saveOrUpdate(SubjectDTO subjectDTO){
        subjectRepository.save(toSubject.convert(subjectDTO));
    }

    public void delete(Long id){
        subjectRepository.deleteById(id);
    }


}
