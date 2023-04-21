package com.example.schooltest.repositories;

import com.example.schooltest.models.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findAll(Sort sort);
}
