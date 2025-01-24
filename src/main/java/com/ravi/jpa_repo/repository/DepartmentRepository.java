package com.ravi.jpa_repo.repository;

import com.ravi.jpa_repo.api.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
