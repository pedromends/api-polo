package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
