package com.tutorial.crud.service;

import com.tutorial.crud.model.Country;
import com.tutorial.crud.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department getDepartment(Integer id); //ok
    Department save(Department department); //ok
    Optional<Department> findById(Integer id);
    List<Department> listAllDepartment(); //ok
    Department edit(Department department, Integer id);
    void deleteById(Integer id);

}
