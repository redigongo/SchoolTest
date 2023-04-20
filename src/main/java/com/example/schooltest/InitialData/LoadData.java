package com.example.schooltest.InitialData;

import com.example.schooltest.models.School;
import com.example.schooltest.models.Subject;
import com.example.schooltest.models.Teacher;
import com.example.schooltest.repositories.SchoolRepository;
import com.example.schooltest.repositories.SubjectRepository;
import com.example.schooltest.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
@AllArgsConstructor
public class LoadData implements CommandLineRunner {

    private final TeacherRepository teacherRepository;
    private final SchoolRepository schoolRepository;
    private final SubjectRepository subjectRepository;
    @Override
    public void run(String... args) throws Exception {
        saveTeachers();
    }

    public void saveTeachers(){
        if (teacherRepository.count()==0){
            School school = new School();
            school.setName("School 1");

            Teacher teacher1 = new Teacher();
            teacher1.setName("Teacher 1");

        }
    }
}
