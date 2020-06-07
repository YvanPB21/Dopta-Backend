package com.dopta.repository;

import com.dopta.model.Corporation;
import com.dopta.model.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentsRepository extends JpaRepository<Departments,Integer> {
}
