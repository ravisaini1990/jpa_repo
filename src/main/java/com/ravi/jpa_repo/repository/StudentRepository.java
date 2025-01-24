package com.ravi.jpa_repo.repository;

import com.ravi.jpa_repo.api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
