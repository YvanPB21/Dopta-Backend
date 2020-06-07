package com.dopta.service;

import com.dopta.model.Departments;
import com.dopta.model.Districts;

import java.util.List;

public interface DepartmentsService {
    Departments getDepartments(Integer id);
    List<Departments> listAll();
    Departments save(Departments departments);
    Departments edit(Departments departments, Integer id);
    void deleteById(Integer id);
}
