package com.dopta.service;

import com.dopta.model.Department;

import java.util.List;

public interface DepartmentService {
    Department getDepartment(Integer id);
    List<Department> listAll();
    Department save(Department department);
    Department edit(Department department, Integer id);
    void deleteById(Integer id);
}
