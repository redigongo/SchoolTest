package com.example.schooltest.repositories;

import com.example.schooltest.models.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School,Long> {
}
