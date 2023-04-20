package com.example.schooltest.repositories;

import com.example.schooltest.models.StudentFee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentFeeRepository extends JpaRepository<StudentFee,Long> {
}
