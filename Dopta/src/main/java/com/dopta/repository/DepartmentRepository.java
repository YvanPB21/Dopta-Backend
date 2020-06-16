package com.dopta.repository;

import com.dopta.model.Corporation;
import com.dopta.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
