package com.example.schooltest.converter;

import com.example.schooltest.dto.SchoolDTO;
import com.example.schooltest.models.School;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SchoolDTOtoSchool implements Converter<SchoolDTO,School> {
    @Override
    public School convert(SchoolDTO source) {
        if(source!=null){
            School school = new School();
            if (source.getId()!=null){
                school.setId(source.getId());
            }
            school.setName(source.getName());
        }
        return null;
    }
}
