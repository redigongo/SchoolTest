package com.example.schooltest.InitialData;

import com.example.schooltest.models.*;
import com.example.schooltest.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class LoadData implements CommandLineRunner {

    private final TeacherRepository teacherRepository;
    private final SchoolRepository schoolRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final StudentFeeRepository studentFeeRepository;
    @Override
    public void run(String... args) throws Exception {
        saveTeachers();
    }

    public void saveTeachers(){
        if (teacherRepository.count()==0){
            School school = new School();
            school.setName("School 1");
            school.setTotalMoneySpent(4000);
            school.setTotalMoneyEarned(6000);
            schoolRepository.save(school);

            Subject subject1 = new Subject();
            subject1.setName("Subject 1");
            subjectRepository.save(subject1);

            Subject subject2 = new Subject();
            subject2.setName("Subject 2");
            subjectRepository.save(subject2);

            Subject subject3 = new Subject();
            subject3.setName("Subject 3");
            subjectRepository.save(subject3);

            Subject subject4 = new Subject();
            subject4.setName("Subject 4");
            subjectRepository.save(subject4);

            Teacher teacher1 = new Teacher();
            teacher1.setName("Teacher 1");
            teacher1.setSalary(1000);
            teacher1.setSchool(school);
            teacher1.setSubject(subject1);
            teacherRepository.save(teacher1);

            Teacher teacher2 = new Teacher();
            teacher2.setName("Teacher 2");
            teacher2.setSalary(1000);
            teacher2.setSchool(school);
            teacher2.setSubject(subject2);
            teacherRepository.save(teacher2);

            Teacher teacher3 = new Teacher();
            teacher3.setName("Teacher 3");
            teacher3.setSalary(1000);
            teacher3.setSchool(school);
            teacher3.setSubject(subject3);
            teacherRepository.save(teacher3);

            Teacher teacher4 = new Teacher();
            teacher4.setName("Teacher 4");
            teacher4.setSalary(1000);
            teacher4.setSchool(school);
            teacher4.setSubject(subject4);
            teacherRepository.save(teacher4);

            List<Student> students = new ArrayList<>();
            for (int i = 1; i<=10; i++){
                Student student = new Student();
                student.setName("Student " + i);
                student.setSchool(school);
                student.setSubject(i<=5 ? subject1 : subject2);
                student.setFeesRemaining(5000);
                students.add(student);
            }
            Student aStudent = new Student();
            aStudent.setName("Annie Brice");
            aStudent.setSchool(school);
            aStudent.setSubject(subject3);
            aStudent.setFeesRemaining(5000);
            students.add(aStudent);

            Student zStudent = new Student();
            zStudent.setName("Zoey Peterson");
            zStudent.setSchool(school);
            zStudent.setSubject(subject4);
            zStudent.setFeesRemaining(5000);
            students.add(zStudent);

            studentRepository.saveAll(students);

            StudentFee studentFee1 = new StudentFee();
            studentFee1.setAmount(2000);
            studentFee1.setStudent(aStudent);
            studentFeeRepository.save(studentFee1);

            StudentFee studentFee2 = new StudentFee();
            studentFee2.setAmount(2000);
            studentFee2.setStudent(aStudent);
            studentFeeRepository.save(studentFee2);

            StudentFee studentFee3 = new StudentFee();
            studentFee3.setAmount(2000);
            studentFee3.setStudent(zStudent);
            studentFeeRepository.save(studentFee3);

        }
    }
}
